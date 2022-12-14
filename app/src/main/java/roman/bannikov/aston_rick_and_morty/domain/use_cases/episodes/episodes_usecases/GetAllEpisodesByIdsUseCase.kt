package roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases

import roman.bannikov.aston_rick_and_morty.domain.models.episode.EpisodeDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodesRepository

class GetAllEpisodesByIdsUseCase(
    private val episodesRepository: EpisodesRepository
) {

    suspend fun execute(
        ids: List<Int>
    ): List<EpisodeDomain> {
        return episodesRepository.getAllEpisodesByIds(ids = ids)
    }
}