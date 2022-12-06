package roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories

import roman.bannikov.aston_rick_and_morty.domain.models.character.CharacterModel

interface CharacterDetailsRepository {

    suspend fun getCharacterById(id: Int): CharacterModel
}