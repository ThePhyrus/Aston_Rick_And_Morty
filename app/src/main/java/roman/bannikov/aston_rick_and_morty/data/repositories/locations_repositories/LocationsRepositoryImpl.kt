package roman.bannikov.aston_rick_and_morty.data.repositories.locations_repositories

import androidx.paging.*
import roman.bannikov.aston_rick_and_morty.data.mapper.entity_to_domain_model.LocationEntityToDomainModel
import roman.bannikov.aston_rick_and_morty.data.paging.locations_paging.LocationsRemoteMediator
import roman.bannikov.aston_rick_and_morty.data.remote.api.locations.LocationsApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.LocationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


@ExperimentalPagingApi
class LocationsRepositoryImpl(
    private val locationsApi: LocationsApi,
    private val db: RickAndMortyDatabase
) : LocationsRepository {

    override fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationModel>> {

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
            remoteMediator = LocationsRemoteMediator(
                locationsApi = locationsApi,
                db = db,
                name = name,
                type = type,
                dimension = dimension
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it ->
                LocationEntityToDomainModel().transform(it)
            }
        }
    }

    override suspend fun getAllLocationsByIds(ids: List<Int>): List<LocationModel> {
        TODO("Not yet implemented")
    }
}