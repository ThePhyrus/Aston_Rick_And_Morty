package roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_details_use_cases

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeModel
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.EpisodeDetailsRepository

class GetEpisodeByIdUseCase(
    private val episodeDetailsRepository: EpisodeDetailsRepository
) {

    suspend fun execute(id: Int): EpisodeModel =
        episodeDetailsRepository.getEpisodeById(id = id)
}