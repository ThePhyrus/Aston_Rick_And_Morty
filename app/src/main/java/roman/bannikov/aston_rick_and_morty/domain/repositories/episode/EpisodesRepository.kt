package roman.bannikov.aston_rick_and_morty.domain.repositories.episode

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain

interface EpisodesRepository {

    fun getAllEpisodes(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeDomain>>

    suspend fun getAllEpisodesByIds(ids: List<Int>): List<EpisodeDomain>
}