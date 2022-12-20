package roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.GetCharacterFiltersRepository

class GetListCharactersTypesUseCase(
    private val getCharacterFiltersRepository: GetCharacterFiltersRepository
) {

    fun execute(): Flow<List<String>> =
        getCharacterFiltersRepository.getListCharactersTypes().map { it.distinct() }
}