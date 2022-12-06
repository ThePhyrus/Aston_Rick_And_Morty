package roman.bannikov.aston_rick_and_morty.data.repositories.characters_repositories

import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.GetCharacterFiltersRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterFiltersRepositoryImpl(
    private val db: RickAndMortyDatabase
) : GetCharacterFiltersRepository {

    override fun getListCharactersSpecies(): Flow<List<String>> =
        db.getCharacterDao().getSpecies()


    override fun getListCharactersTypes(): Flow<List<String>> =
        db.getCharacterDao().getTypes()

}