package roman.bannikov.aston_rick_and_morty.data.models.pages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_pages")
data class CharacterPages(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)