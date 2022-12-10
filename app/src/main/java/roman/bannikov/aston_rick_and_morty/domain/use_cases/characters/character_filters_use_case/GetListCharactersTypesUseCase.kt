package roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.GetCharacterFiltersRepository

class GetListCharactersTypesUseCase(
    private val getCharacterFiltersRepository: GetCharacterFiltersRepository
) {

    fun execute(): Flow<List<String>> =
        getCharacterFiltersRepository.getListCharactersTypes().map { it.distinct() }
}