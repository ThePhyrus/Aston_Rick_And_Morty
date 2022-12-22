package roman.bannikov.aston_rick_and_morty.data.repositories.episode

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeFilterRepository

class EpisodeFilterRepositoryImpl(
    private val database: AppDatabase
) : EpisodeFilterRepository {

    override fun getListEpisodes(): Flow<List<String>> =
        database.getEpisodeDao().getEpisodes()
}