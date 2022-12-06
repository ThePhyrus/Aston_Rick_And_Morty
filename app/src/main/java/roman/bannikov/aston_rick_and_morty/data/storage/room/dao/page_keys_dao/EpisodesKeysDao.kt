package roman.bannikov.aston_rick_and_morty.data.storage.room.dao.page_keys_dao

import androidx.room.*
import roman.bannikov.aston_rick_and_morty.data.models.page_keys.EpisodesPageKeys

@Dao
interface EpisodesKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodesKeys(remoteKeysEpisodes: List<EpisodesPageKeys>?)

    @Query("SELECT * FROM EPISODES_PAGE_KEYS WHERE id =:id")
    suspend fun getEpisodesRemoteKeys(id: Int): EpisodesPageKeys

    @Query("DELETE FROM EPISODES_PAGE_KEYS")
    suspend fun deleteAllEpisodesRemoteKeys()
}