package roman.bannikov.aston_rick_and_morty.data.repositories.location

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.data.mapper.LocationDataToLocationDomain
import roman.bannikov.aston_rick_and_morty.data.paging.LocationRemoteMediator
import roman.bannikov.aston_rick_and_morty.data.api.location.LocationApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationsRepository


@ExperimentalPagingApi
class LocationRepositoryImpl(
    private val locationApi: LocationApi,
    private val db: RickAndMortyDatabase
) : LocationsRepository {

    override fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationDomain>> {

        val pagingSourceFactory =
            {
                db.getLocationDao().getFilteredLocations(
                    name = name,
                    type = type,
                    dimension = dimension
                )
            }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ),
            remoteMediator = LocationRemoteMediator(
                locationApi = locationApi,
                db = db,
                name = name,
                type = type,
                dimension = dimension
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it ->
                LocationDataToLocationDomain().transform(it)
            }
        }
    }

    override suspend fun getAllLocationsById(id: List<Int>): List<LocationDomain> {
        TODO("Not yet implemented")
    }
}