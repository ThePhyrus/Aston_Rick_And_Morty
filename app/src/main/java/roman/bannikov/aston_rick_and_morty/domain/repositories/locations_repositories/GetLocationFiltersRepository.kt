package roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories

import kotlinx.coroutines.flow.Flow

interface GetLocationFiltersRepository {

    fun getListLocationsTypes(): Flow<List<String>>

    fun getListLocationsDimensions(): Flow<List<String>>
}