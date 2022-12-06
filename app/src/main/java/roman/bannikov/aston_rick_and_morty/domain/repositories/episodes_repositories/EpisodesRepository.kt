package roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories

import androidx.paging.PagingData
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeModel
import kotlinx.coroutines.flow.Flow

interface EpisodesRepository {

    fun getAllEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>>

    suspend fun getAllEpisodesByIds(ids: List<Int>): List<EpisodeModel>
}