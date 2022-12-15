package roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.GetLocationFiltersRepository

class GetListLocationsDimensionsUseCase(
    private val getLocationFiltersRepository: GetLocationFiltersRepository
) {

    fun execute(): Flow<List<String>> =
        getLocationFiltersRepository.getListLocationsDimensions().map { it.distinct() }
}