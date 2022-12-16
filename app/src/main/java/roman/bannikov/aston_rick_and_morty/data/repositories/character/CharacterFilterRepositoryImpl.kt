package roman.bannikov.aston_rick_and_morty.data.repositories.character

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.GetCharacterFiltersRepository

class CharacterFilterRepositoryImpl(
    private val db: AppDatabase
) : GetCharacterFiltersRepository {

    override fun getListCharactersSpecies(): Flow<List<String>> =
        db.getCharacterDao().getSpecies()


    override fun getListCharactersTypes(): Flow<List<String>> =
        db.getCharacterDao().getTypes()

}