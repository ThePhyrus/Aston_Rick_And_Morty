package roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases

import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharactersRepository

class GetAllCharactersByIdsUseCase(
    private val charactersRepository: CharactersRepository
) {

    suspend fun execute(
        ids: List<Int>
    ): List<CharacterModel> {
        return charactersRepository.getAllCharactersByIds(
            ids = ids
        )
    }
}