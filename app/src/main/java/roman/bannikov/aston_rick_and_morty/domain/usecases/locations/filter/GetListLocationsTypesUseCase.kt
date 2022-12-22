package roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationFilterRepository

class GetListLocationsTypesUseCase(
    private val locationFilterRepository: LocationFilterRepository
) {

    fun execute(): Flow<List<String>> =
        locationFilterRepository.getListLocationsTypes().map { it.distinct() }
}