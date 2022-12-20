package roman.bannikov.aston_rick_and_morty.data.models.character

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_table")
data class CharacterData(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    @ColumnInfo(name = "origin")
    val origin: OriginLocation,
    @ColumnInfo(name = "location")
    val location: OriginLocation,
    @ColumnInfo(name = "episodes")
    val episode: List<String>
)