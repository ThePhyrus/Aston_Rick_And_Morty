package roman.bannikov.aston_rick_and_morty.data.api.episode

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import roman.bannikov.aston_rick_and_morty.data.models.episode.EpisodeData

interface EpisodeDetailsApi {

    /**
     * Get character by id
     * @param id - Episode id
     * @return  -  Response from the server
     */
    @GET("episode/{id}")
    suspend fun getEpisodeById(
        @Path("id") id: Int
    ): Response<EpisodeData>
}