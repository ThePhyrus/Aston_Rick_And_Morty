package roman.bannikov.aston_rick_and_morty.data.repositories.episode

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.GetEpisodeFiltersRepository

class EpisodeFilterRepositoryImpl(
    private val db: AppDatabase
) : GetEpisodeFiltersRepository {

    override fun getListEpisodes(): Flow<List<String>> =
        db.getEpisodeDao().getEpisodes()
}