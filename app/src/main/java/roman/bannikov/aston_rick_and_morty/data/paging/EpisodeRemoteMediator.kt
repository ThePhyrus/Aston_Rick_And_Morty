package roman.bannikov.aston_rick_and_morty.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import retrofit2.HttpException
import roman.bannikov.aston_rick_and_morty.data.models.PagedResponse
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData
import roman.bannikov.aston_rick_and_morty.data.models.pages.EpisodePages
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import java.io.IOException


@OptIn(ExperimentalPagingApi::class)
class EpisodeRemoteMediator(
    private val episodeApi: EpisodeApi,
    private val database: AppDatabase,
    private val name: String?,
    private val episode: String?
) : RemoteMediator<Int, EpisodeData>() {

    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    private val episodeDao = database.getEpisodeDao()
    private val keyDao = database.getEpisodesKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, EpisodeData>
    ): MediatorResult {


        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {

            val response: PagedResponse<EpisodeData> =
                episodeApi.getEpisodes(
                    page = page,
                    name = name,
                    episode = episode
                )

            val isEndOfList =
                response.info.next == null
                        || response.toString().contains("error")
                        || response.results.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    episodeDao.deleteAllEpisodes()
                    keyDao.deleteAllEpisodesRemoteKeys()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = response.results.map {
                    EpisodePages(it.id, prevPage = prevKey, nextPage = nextKey)
                }
                keyDao.insertAllEpisodesKeys(remoteKeysEpisodes = keys)
                episodeDao.insertAllEpisodes(episodeData = response.results)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, EpisodeData>
    ): EpisodePages? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                keyDao.getEpisodesRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, EpisodeData>
    ): EpisodePages? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { it ->
                keyDao.getEpisodesRemoteKeys(id = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, EpisodeData>
    ): EpisodePages? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let {it ->
                keyDao.getEpisodesRemoteKeys(id = it.id)
            }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, EpisodeData>
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