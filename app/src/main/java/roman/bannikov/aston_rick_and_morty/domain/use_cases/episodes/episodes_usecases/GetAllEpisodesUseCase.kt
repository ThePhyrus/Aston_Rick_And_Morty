package roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases

import androidx.paging.PagingData
import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.EpisodesRepository
import kotlinx.coroutines.flow.Flow

class GetAllEpisodesUseCase(
    private val episodesRepository: EpisodesRepository
) {

    fun execute(
        name: String?,
        episode: String?
    ): Flow<PagingData<EpisodeModel>> {
        return episodesRepository.getAllEpisodes(
            name = name,
            episode = episode
        )
    }
}