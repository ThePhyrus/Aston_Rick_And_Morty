package roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.locations_usecases

import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.LocationsRepository

class GetAllLocationsByIdsUseCase(
    private val locationsRepository: LocationsRepository
) {

    suspend fun execute(
        ids: List<Int>
    ): List<LocationDomain> {
        return locationsRepository.getAllLocationsByIds(
            ids = ids
        )
    }
}