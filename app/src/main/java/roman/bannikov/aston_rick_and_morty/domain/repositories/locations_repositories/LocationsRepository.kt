package roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain

interface LocationsRepository {

    fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationDomain>>

    suspend fun getAllLocationsByIds(ids: List<Int>): List<LocationDomain>
}