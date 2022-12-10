package roman.bannikov.aston_rick_and_morty.data.remote.api.episodes

import retrofit2.Response
import retrofit2.http.*
import roman.bannikov.aston_rick_and_morty.data.models.PagedResponse
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData

interface EpisodesApi {

    /**
     * Get episode page.
     * @param page - Episodes page.
     * @param name - name of episodes.
     * @param episode - Episode.
     * @return - Response from the server.
     */
    @GET("episode/")
    suspend fun getEpisodes(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("episode") episode: String?
    ): PagedResponse<EpisodeData>

    /**
     * Get episodes by ids.
     * @param ids - String with ids. (For ex.: "1,2,3")
     * @return - Response from the server.
     */
    @GET("episode/{ids}")
    suspend fun getEpisodesByIds(
        @Path("ids") ids: String
    ): Response<List<EpisodeData>>
}