package roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories

import kotlinx.coroutines.flow.Flow

interface GetCharacterFiltersRepository {
    fun getListCharactersSpecies(): Flow<List<String>>

    fun getListCharactersTypes(): Flow<List<String>>
}