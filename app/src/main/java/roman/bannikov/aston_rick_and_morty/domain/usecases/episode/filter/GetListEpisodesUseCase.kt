package roman.bannikov.aston_rick_and_morty.domain.usecases.episode.filter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeFilterRepository

class GetListEpisodesUseCase(
    private val episodeFilterRepository: EpisodeFilterRepository
) {

    fun execute(): Flow<List<String>> =
        episodeFilterRepository.getListEpisodes().map { it.distinct() }
}