package roman.bannikov.aston_rick_and_morty.domain.usecases.episode.filter

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.GetEpisodeFiltersRepository

class GetListEpisodesUseCase(
    private val getEpisodeFiltersRepository: GetEpisodeFiltersRepository
) {

    fun execute(): Flow<List<String>> =
        getEpisodeFiltersRepository.getListEpisodes().map { it.distinct() }
}