package roman.bannikov.aston_rick_and_morty.data.models.pages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_pages")
data class EpisodePages(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)