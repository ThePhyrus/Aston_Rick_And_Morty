package roman.bannikov.aston_rick_and_morty.data.models.episode

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode_table")
data class EpisodeData(
    @PrimaryKey val id: Int,
    val name: String,
    val characters: List<String>,
    val air_date: String,
    val episode: String,
)