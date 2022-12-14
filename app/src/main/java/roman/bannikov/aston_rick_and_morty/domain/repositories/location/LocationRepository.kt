package roman.bannikov.aston_rick_and_morty.domain.repositories.location

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain

interface LocationRepository {

    fun getAllLocations(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationDomain>>

    suspend fun getAllLocationsById(id: List<Int>): List<LocationDomain>
}