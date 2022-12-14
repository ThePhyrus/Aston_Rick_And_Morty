package roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_details_use_cases

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeDetailsRepository

class GetEpisodeByIdUseCase(
    private val episodeDetailsRepository: EpisodeDetailsRepository
) {

    suspend fun execute(id: Int): EpisodeDomain =
        episodeDetailsRepository.getEpisodeById(id = id)
}