package roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationRepository

class GetAllLocationsUseCase(
    private val locationRepository: LocationRepository
) {

    fun execute(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationDomain>> {
        return locationRepository.getAllLocations(
            name = name,
            type = type,
            dimension = dimension
        )
    }
}