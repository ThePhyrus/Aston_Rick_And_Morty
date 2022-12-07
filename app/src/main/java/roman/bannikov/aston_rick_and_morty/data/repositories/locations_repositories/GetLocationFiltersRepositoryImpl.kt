package roman.bannikov.aston_rick_and_morty.data.repositories.locations_repositories

import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.GetLocationFiltersRepository
import kotlinx.coroutines.flow.Flow

class GetLocationFiltersRepositoryImpl(
    private val db: RickAndMortyDatabase
) : GetLocationFiltersRepository {

    override fun getListLocationsTypes(): Flow<List<String>> =
        db.getLocationDao().getTypes()

    override fun getListLocationsDimensions(): Flow<List<String>> =
        db.getLocationDao().getDimensions()
}