package roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list

import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationsRepository

class GetAllLocationsByIdsUseCase(
    private val locationsRepository: LocationsRepository
) {

    suspend fun execute(
        ids: List<Int>
    ): List<LocationDomain> {
        return locationsRepository.getAllLocationsById(
            id = ids
        )
    }
}