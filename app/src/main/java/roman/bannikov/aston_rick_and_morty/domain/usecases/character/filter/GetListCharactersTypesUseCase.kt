package roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.GetCharacterFilterRepository

class GetListCharactersTypesUseCase(
    private val getCharacterFilterRepository: GetCharacterFilterRepository
) {

    fun execute(): Flow<List<String>> =
        getCharacterFilterRepository.getListCharactersTypes().map { it.distinct() }
}