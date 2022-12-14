package roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.locations_usecases

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationsRepository

class GetAllLocationsUseCase(
    private val locationsRepository: LocationsRepository
) {

    fun execute(
        name: String?,
        type: String?,
        dimension: String?
    ): Flow<PagingData<LocationDomain>> {
        return locationsRepository.getAllLocations(
            name = name,
            type = type,
            dimension = dimension
        )
    }
}