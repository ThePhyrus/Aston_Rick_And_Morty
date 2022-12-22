package roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeRepository

class GetAllEpisodesByIdsUseCase(
    private val episodeRepository: EpisodeRepository
) {

    suspend fun execute(
        ids: List<Int>
    ): List<EpisodeDomain> {
        return episodeRepository.getAllEpisodesByIds(ids = ids)
    }
}