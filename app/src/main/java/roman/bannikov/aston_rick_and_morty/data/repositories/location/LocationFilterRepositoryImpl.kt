package roman.bannikov.aston_rick_and_morty.data.repositories.location

import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationFilterRepository

class LocationFilterRepositoryImpl(
    private val database: AppDatabase
) : LocationFilterRepository {

    override fun getListLocationsTypes(): Flow<List<String>> =
        database.getLocationDao().getTypes()

    override fun getListLocationsDimensions(): Flow<List<String>> =
        database.getLocationDao().getDimensions()
}