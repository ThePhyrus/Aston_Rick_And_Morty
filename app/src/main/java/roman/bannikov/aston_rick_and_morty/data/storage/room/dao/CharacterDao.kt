package roman.bannikov.aston_rick_and_morty.data.storage.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.models.character.CharacterData


@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCharacters(characters: List<CharacterData?>?)

    @Query("SELECT * FROM CHARACTERS_TABLE")
    fun getAllCharacters(): Flow<List <CharacterData>>

    @Query("DELETE FROM CHARACTERS_TABLE")
    suspend fun deleteAllCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: CharacterData)

    @Query(
        """SELECT * FROM CHARACTERS_TABLE
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
        AND (:status IS NULL OR status LIKE :status)
        AND (:gender IS NULL OR gender LIKE :gender)
        AND (:type IS NULL OR type LIKE :type)
        AND (:species IS NULL OR species LIKE :species)"""
    )
    fun getFilteredCharacters(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?,
    ): PagingSource<Int, CharacterData>

    @Query(
        """SELECT * FROM CHARACTERS_TABLE
        WHERE id IN (:ids)
        ORDER BY id ASC"""
    )
    suspend fun getCharactersByIds(ids: List<Int>): List<CharacterData>

    @Query("SELECT * FROM CHARACTERS_TABLE WHERE id = :id")
    suspend fun getCharacterById(id: Int): CharacterData

    @Query(
        """SELECT type FROM CHARACTERS_TABLE ORDER BY type ASC"""
    )
    fun getTypes(): Flow<List<String>>

    @Query(
        """SELECT species FROM CHARACTERS_TABLE ORDER BY species ASC"""
    )
    fun getSpecies(): Flow<List<String>>
}