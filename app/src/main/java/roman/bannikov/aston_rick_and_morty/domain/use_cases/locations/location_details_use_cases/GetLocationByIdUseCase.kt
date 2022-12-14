package roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_details_use_cases

import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationDetailsRepository

class GetLocationByIdUseCase(
    private val locationDetailsRepository: LocationDetailsRepository
) {

    suspend fun execute(id: Int): LocationDomain =
        locationDetailsRepository.getLocationById(id = id)
}