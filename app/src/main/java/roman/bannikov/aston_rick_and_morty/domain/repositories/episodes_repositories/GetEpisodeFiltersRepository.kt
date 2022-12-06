package roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories

import kotlinx.coroutines.flow.Flow

interface GetEpisodeFiltersRepository {

    fun getListEpisodes(): Flow<List<String>>
}