package roman.bannikov.aston_rick_and_morty.domain.repositories.character

import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterDomain

interface CharacterDetailsRepository {

    suspend fun getCharacterById(id: Int): CharacterDomain
}