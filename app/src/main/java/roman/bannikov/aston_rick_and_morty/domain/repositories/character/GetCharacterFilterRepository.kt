package roman.bannikov.aston_rick_and_morty.domain.repositories.character

import kotlinx.coroutines.flow.Flow

interface GetCharacterFilterRepository {
    fun getListCharactersSpecies(): Flow<List<String>>

    fun getListCharactersTypes(): Flow<List<String>>
}