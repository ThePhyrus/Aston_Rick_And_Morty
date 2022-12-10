package roman.bannikov.aston_rick_and_morty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import roman.bannikov.aston_rick_and_morty.data.models.PagedResponse
import roman.bannikov.aston_rick_and_morty.data.models.location.LocationData
import roman.bannikov.aston_rick_and_morty.data.models.pages.LocationPages
import roman.bannikov.aston_rick_and_morty.data.remote.api.location.LocationApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class LocationRemoteMediator(
    private val locationApi: LocationApi,
    private val db: RickAndMortyDatabase,
    private val name: String?,
    private val type: String?,
    private val dimension: String?
) : RemoteMediator<Int, LocationData>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    private val locationDao = db.getLocationDao()
    private val keyDao = db.getLocationsKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, LocationData>
    ): MediatorResult {

        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {

            val response: PagedResponse<LocationData> =
                locationApi.getLocations(
                    page = page,
                    name = name,
                    type = type,
                    dimension = dimension
                )
            val isEndOfList =
                response.info.next == null
                        || response.toString().contains("error")
                        || response.results.isEmpty()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    locationDao.deleteAllLocations()
                    keyDao.deleteAllLocationRemoteKeys()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map {
                    LocationPages(it.id, prevPage = prevKey, nextPage = nextKey)
                }
                keyDao.insertAllLocationKeys(remoteKeysLocations = keys)
                locationDao.insertAllLocations(locationData = response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, LocationData>
    ): LocationPages? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keyDao.getLocationRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, LocationData>
    ): LocationPages? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { it ->
                keyDao.getLocationRemoteKeys(id = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, LocationData>
    ): LocationPages? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let {it ->
                keyDao.getLocationRemoteKeys(id = it.id)
            }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, LocationData>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1) ?: 1
            }

            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                remoteKeys?.prevPage ?: return MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null
                )
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                val nextPage = remoteKeys?.nextPage
                return nextPage ?: RemoteMediator.MediatorResult.Success(
                    endOfPaginationReached = remoteKeys != null)
            }
        }
    }
}