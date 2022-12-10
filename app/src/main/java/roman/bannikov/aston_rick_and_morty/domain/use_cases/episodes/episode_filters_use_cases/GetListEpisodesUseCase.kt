package roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_filters_use_cases

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.GetEpisodeFiltersRepository

class GetListEpisodesUseCase(
    private val getEpisodeFiltersRepository: GetEpisodeFiltersRepository
) {

    fun execute(): Flow<List<String>> =
        getEpisodeFiltersRepository.getListEpisodes().map { it.distinct() }
}