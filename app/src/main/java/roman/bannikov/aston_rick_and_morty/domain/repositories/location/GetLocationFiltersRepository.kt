package roman.bannikov.aston_rick_and_morty.domain.repositories.location

import kotlinx.coroutines.flow.Flow

interface GetLocationFiltersRepository {

    fun getListLocationsTypes(): Flow<List<String>>

    fun getListLocationsDimensions(): Flow<List<String>>
}