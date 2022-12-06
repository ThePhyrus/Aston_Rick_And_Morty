package roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_details_use_cases

import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.LocationDetailsRepository

class GetLocationByIdUseCase(
    private val locationDetailsRepository: LocationDetailsRepository
) {

    suspend fun execute(id: Int): LocationModel =
        locationDetailsRepository.getLocationById(id = id)
}