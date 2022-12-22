package roman.bannikov.aston_rick_and_morty.domain.usecases.character.details

import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterDetailsRepository


class GetCharacterByIdUseCase(
    private val characterDetailsRepository: CharacterDetailsRepository
) {

    suspend fun execute(id: Int): CharacterDomain =
        characterDetailsRepository.getCharacterById(id = id)
}