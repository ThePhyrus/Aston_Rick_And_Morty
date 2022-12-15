package roman.bannikov.aston_rick_and_morty.domain.usecases.episode.details

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeDetailsRepository

class GetEpisodeByIdUseCase(
    private val episodeDetailsRepository: EpisodeDetailsRepository
) {

    suspend fun execute(id: Int): EpisodeDomain =
        episodeDetailsRepository.getEpisodeById(id = id)
}