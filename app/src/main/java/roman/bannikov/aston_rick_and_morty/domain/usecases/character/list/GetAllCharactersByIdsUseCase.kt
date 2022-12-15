package roman.bannikov.aston_rick_and_morty.domain.usecases.character.list

import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterRepository

class GetAllCharactersByIdsUseCase(
    private val characterRepository: CharacterRepository
) {

    suspend fun execute(
        ids: List<Int>
    ): List<CharacterDomain> {
        return characterRepository.getAllCharactersByIds(
            ids = ids
        )
    }
}