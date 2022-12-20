package roman.bannikov.aston_rick_and_morty.data.models.pages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_pages")
data class LocationPages(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)