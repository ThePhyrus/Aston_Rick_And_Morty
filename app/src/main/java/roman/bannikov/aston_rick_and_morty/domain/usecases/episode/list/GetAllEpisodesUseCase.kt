package roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodesRepository

class GetAllEpisodesUseCase(
    private val episodesRepository: EpisodesRepository
) {

    fun execute(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeDomain>> {
        return episodesRepository.getAllEpisodes(
            name = name,
            episode = episode
        )
    }
}