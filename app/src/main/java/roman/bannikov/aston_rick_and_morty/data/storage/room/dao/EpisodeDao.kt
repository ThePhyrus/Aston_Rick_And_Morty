package roman.bannikov.aston_rick_and_morty.data.storage.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData


@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodeData: List<EpisodeData?>?)

    @Query("DELETE FROM episode_table")
    suspend fun deleteAllEpisodes()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episodeData: EpisodeData)

    @Query(
        """SELECT * FROM episode_table
        WHERE id IN (:ids)
        ORDER BY id ASC"""
    )
    suspend fun getEpisodesByIds(ids: List<Int>): List<EpisodeData>

    @Query("SELECT * FROM episode_table WHERE id = :id")
    suspend fun getEpisodeById(id: Int): EpisodeData

    @Query(
        """SELECT episode FROM episode_table ORDER BY episode ASC"""
    )
    fun getEpisodes(): Flow<List<String>>

    @Query(
        """SELECT * FROM episode_table
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
        AND (:episode IS NULL OR episode LIKE :episode)"""
    )
    fun getFilteredEpisodes(
        name: String?,
        episode: String?,
    ): PagingSource<Int, EpisodeData>
}