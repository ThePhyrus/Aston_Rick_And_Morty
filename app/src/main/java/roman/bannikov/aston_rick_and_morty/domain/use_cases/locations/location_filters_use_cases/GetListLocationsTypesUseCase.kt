package roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_filters_use_cases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.GetLocationFiltersRepository

class GetListLocationsTypesUseCase(
    private val getLocationFiltersRepository: GetLocationFiltersRepository
) {

    fun execute(): Flow<List<String>> =
        getLocationFiltersRepository.getListLocationsTypes().map { it.distinct() }
}