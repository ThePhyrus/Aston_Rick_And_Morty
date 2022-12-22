package roman.bannikov.aston_rick_and_morty.domain.repositories.episode

import kotlinx.coroutines.flow.Flow

interface EpisodeFilterRepository {

    fun getListEpisodes(): Flow<List<String>>
}