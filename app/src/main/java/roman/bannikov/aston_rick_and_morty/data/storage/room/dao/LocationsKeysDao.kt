package roman.bannikov.aston_rick_and_morty.data.storage.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import roman.bannikov.aston_rick_and_morty.data.models.pages.LocationPages

@Dao
interface LocationsKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllLocationKeys(remoteKeysLocations: List<LocationPages>)

    @Query("SELECT * FROM location_pages WHERE id =:id")
    suspend fun getLocationRemoteKeys(id: Int): LocationPages

    @Query("DELETE FROM location_pages")
    suspend fun deleteAllLocationRemoteKeys()
}