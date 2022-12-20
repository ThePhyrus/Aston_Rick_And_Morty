package roman.bannikov.aston_rick_and_morty.data.repositories.location

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.GetLocationFiltersRepository

class LocationFilterRepositoryImpl(
    private val db: AppDatabase
) : GetLocationFiltersRepository {

    override fun getListLocationsTypes(): Flow<List<String>> =
        db.getLocationDao().getTypes()

    override fun getListLocationsDimensions(): Flow<List<String>> =
        db.getLocationDao().getDimensions()
}