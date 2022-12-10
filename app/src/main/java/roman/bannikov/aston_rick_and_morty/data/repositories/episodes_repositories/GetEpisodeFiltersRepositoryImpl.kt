package roman.bannikov.aston_rick_and_morty.data.repositories.episodes_repositories

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.GetEpisodeFiltersRepository

class GetEpisodeFiltersRepositoryImpl(
    private val db: RickAndMortyDatabase
) : GetEpisodeFiltersRepository {

    override fun getListEpisodes(): Flow<List<String>> =
        db.getEpisodeDao().getEpisodes()
}