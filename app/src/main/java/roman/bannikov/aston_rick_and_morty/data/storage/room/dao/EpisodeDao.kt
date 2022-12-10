package roman.bannikov.aston_rick_and_morty.data.storage.room.dao

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData


@Dao
interface EpisodeDao {

    /**
     * Add all episodes.
     * With the replacement of the same episodes.
     * This method is used when we request a list of episodes from the server.
     *
     * @param episodeData - Episodes List.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEpisodes(episodeData: List<EpisodeData?>?)



    /**
     * Delete all episodes for pagination.
     *
     * @return
     */
    @Query("DELETE FROM EPISODES_TABLE")
    suspend fun deleteAllEpisodes()

    /**
     * Add episode.
     * With the replacement of the same episode.
     * This method is used when we request a episode from the server (by id).
     *
     * @param episodeData - Episode.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEpisode(episodeData: EpisodeData)

    /**
     * Get all filtered episodes with pagination. (name, episode).
     *
     * @param name - Episode name.
     * @param episode - Episode status.
     *
     * @return
     */
    @Query(
        """SELECT * FROM EPISODES_TABLE
        WHERE (:name IS NULL OR name LIKE '%' || :name || '%')
        AND (:episode IS NULL OR episode LIKE :episode)"""
    )
    fun getFilteredEpisodes(
        name: String?,
        episode: String?,
    ): PagingSource<Int, EpisodeData>

    /**
     * Get all episodes by ids without pagination.
     *
     * @param ids - Episodes ids.
     *
     * @return
     */
    @Query(
        """SELECT * FROM EPISODES_TABLE
        WHERE id IN (:ids)
        ORDER BY id ASC"""
    )
    suspend fun getEpisodesByIds(ids: List<Int>): List<EpisodeData>

    /**
     * Get episode by id
     *
     * @param id - Episode id.
     */
    @Query("SELECT * FROM EPISODES_TABLE WHERE id = :id")
    suspend fun getEpisodeById(id: Int): EpisodeData

    /**
     * Get all episodes from db.
     *
     * @return Flow with Episode's types.
     */
    @Query(
        """SELECT episode FROM EPISODES_TABLE ORDER BY episode ASC"""
    )
    fun getEpisodes(): Flow<List<String>>
}