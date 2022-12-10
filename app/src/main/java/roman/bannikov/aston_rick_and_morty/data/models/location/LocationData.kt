package roman.bannikov.aston_rick_and_morty.data.models.location

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LOCATIONS_TABLE")
data class LocationData(
    @PrimaryKey val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    @ColumnInfo(name = "residents")
    val residents: List<String>
)