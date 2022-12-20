package roman.bannikov.aston_rick_and_morty.data.storage.room.dao

import androidx.room.*
import roman.bannikov.aston_rick_and_morty.data.models.pages.EpisodePages

@Dao
interface EpisodesKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodesKeys(remoteKeysEpisodes: List<EpisodePages>?)

    @Query("SELECT * FROM episode_pages WHERE id =:id")
    suspend fun getEpisodesRemoteKeys(id: Int): EpisodePages

    @Query("DELETE FROM episode_pages")
    suspend fun deleteAllEpisodesRemoteKeys()
}