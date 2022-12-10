package roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_details_use_cases

import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharacterDetailsRepository

class GetCharacterByIdUseCase(
    private val characterDetailsRepository: CharacterDetailsRepository
) {

    suspend fun execute(id: Int): CharacterDomain =
        characterDetailsRepository.getCharacterById(id = id)
}