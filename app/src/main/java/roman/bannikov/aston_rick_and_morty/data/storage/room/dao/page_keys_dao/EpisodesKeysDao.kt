package roman.bannikov.aston_rick_and_morty.data.storage.room.dao.page_keys_dao

import androidx.room.*
import roman.bannikov.aston_rick_and_morty.data.models.pages.EpisodePages

@Dao
interface EpisodesKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodesKeys(remoteKeysEpisodes: List<EpisodePages>?)

    @Query("SELECT * FROM EPISODES_PAGE_KEYS WHERE id =:id")
    suspend fun getEpisodesRemoteKeys(id: Int): EpisodePages

    @Query("DELETE FROM EPISODES_PAGE_KEYS")
    suspend fun deleteAllEpisodesRemoteKeys()
}