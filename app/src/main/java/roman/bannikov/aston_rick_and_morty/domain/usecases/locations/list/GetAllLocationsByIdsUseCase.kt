package roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list

import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationRepository

class GetAllLocationsByIdsUseCase(
    private val locationRepository: LocationRepository
) {

    suspend fun execute(
        ids: List<Int>
    ): List<LocationDomain> {
        return locationRepository.getAllLocationsById(
            id = ids
        )
    }
}