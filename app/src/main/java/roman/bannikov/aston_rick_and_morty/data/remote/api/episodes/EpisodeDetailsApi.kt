package roman.bannikov.aston_rick_and_morty.data.remote.api.episodes

import roman.bannikov.aston_rick_and_morty.data.models.episodes.Episode
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeDetailsApi {

    /**
     * Get character by id
     * @param id - Episode id
     * @return  -  Response from the server
     */
    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): Response<Episode>
}