package roman.bannikov.aston_rick_and_morty.data.repositories.episode

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.GetEpisodeFiltersRepository

class EpisodeFilterRepositoryImpl(
    private val db: RickAndMortyDatabase
) : GetEpisodeFiltersRepository {

    override fun getListEpisodes(): Flow<List<String>> =
        db.getEpisodeDao().getEpisodes()
}