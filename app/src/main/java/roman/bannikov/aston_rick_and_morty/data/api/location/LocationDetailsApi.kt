package roman.bannikov.aston_rick_and_morty.data.api.location

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import roman.bannikov.aston_rick_and_morty.data.models.location.LocationData

interface LocationDetailsApi {

    /**
     * Get character by id
     * @param id - Location id
     * @return  -  Response from the server
     */
    @GET("location/{id}")
    suspend fun getLocationById(
        @Path("id") id: Int
    ): Response<LocationData>
}