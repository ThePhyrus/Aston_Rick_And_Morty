package roman.bannikov.aston_rick_and_morty.data.storage.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.models.location.LocationData


@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLocations(locationData: List<LocationData?>?)

    @Query("DELETE FROM location_table")
    suspend fun deleteAllLocations()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(locationData: LocationData)


    @Query("SELECT * FROM location_table WHERE id = :id")
    suspend fun getLocationById(id: Int): LocationData

    @Query(
        """SELECT type FROM location_table ORDER BY type ASC"""
    )
    fun getTypes(): Flow<List<String>>

    @Query(
        """SELECT dimension FROM location_table ORDER BY dimension ASC"""
    )
    fun getDimensions(): Flow<List<String>>

    @Query(
        """SELECT * FROM location_table
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
        AND (:type IS NULL OR type LIKE :type)
        AND (:dimension IS NULL OR dimension LIKE :dimension)"""
    )
    fun getFilteredLocations(
        name: String?,
        type: String?,
        dimension: String?,
    ):  PagingSource<Int, LocationData>
}