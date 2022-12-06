package roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_filters_use_cases

import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.GetEpisodeFiltersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetListEpisodesUseCase(
    private val getEpisodeFiltersRepository: GetEpisodeFiltersRepository
) {

    fun execute(): Flow<List<String>> =
        getEpisodeFiltersRepository.getListEpisodes().map { it.distinct() }
}