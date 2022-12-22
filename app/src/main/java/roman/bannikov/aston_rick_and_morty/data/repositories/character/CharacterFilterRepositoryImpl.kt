package roman.bannikov.aston_rick_and_morty.data.repositories.character

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.GetCharacterFilterRepository

class CharacterFilterRepositoryImpl(
    private val database: AppDatabase
) : GetCharacterFilterRepository {

    override fun getListCharactersSpecies(): Flow<List<String>> =
        database.getCharacterDao().getSpecies()


    override fun getListCharactersTypes(): Flow<List<String>> =
        database.getCharacterDao().getTypes()

}