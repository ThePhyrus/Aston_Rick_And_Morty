package roman.bannikov.aston_rick_and_morty.data.repositories.locations_repositories

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import roman.bannikov.aston_rick_and_morty.data.mapper.LocationDataToLocationDomain
import roman.bannikov.aston_rick_and_morty.data.models.location.LocationData
import roman.bannikov.aston_rick_and_morty.data.remote.api.locations.LocationDetailsApi
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.models.location.LocationDomain
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.LocationDetailsRepository
import java.io.IOException


class LocationDetailsRepositoryImpl(
    private val locationDetailsApi: LocationDetailsApi,
    private val db: RickAndMortyDatabase
) : LocationDetailsRepository {

    override suspend fun getLocationById(id: Int): LocationDomain = withContext(Dispatchers.IO) {
        try {
            val locationDataFromApi: Response<LocationData> =
                locationDetailsApi.getLocationById(id = id)
            if (locationDataFromApi.isSuccessful) {
                locationDataFromApi.body()
                    ?.let { db.getLocationDao().insertLocation(locationData = it) }
            }

        } catch (e: HttpException) {
            Log.e("Log", "${e.code()}")
        } catch (e: IOException) {
            Log.e("Log", "${e.message}")
        }

        return@withContext LocationDataToLocationDomain().transform(
            db.getLocationDao().getLocationById(id = id)
        )
    }
}