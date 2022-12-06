package roman.bannikov.aston_rick_and_morty.data.storage.room.dao.page_keys_dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import roman.bannikov.aston_rick_and_morty.data.models.page_keys.CharactersPageKeys

@Dao
interface CharactersKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharactersKeys(remoteKeysCharacters: List<CharactersPageKeys>)

    @Query("SELECT * FROM CHARACTERS_PAGE_KEYS WHERE id =:id")
    suspend fun getCharactersRemoteKeys(id: Int): CharactersPageKeys

    @Query("DELETE FROM CHARACTERS_PAGE_KEYS")
    suspend fun deleteAllCharactersRemoteKeys()
}