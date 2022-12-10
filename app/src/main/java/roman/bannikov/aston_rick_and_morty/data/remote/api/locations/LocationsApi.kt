package roman.bannikov.aston_rick_and_morty.data.remote.api.locations

import retrofit2.Response
import retrofit2.http.*
import roman.bannikov.aston_rick_and_morty.data.models.PagedResponse
import roman.bannikov.aston_rick_and_morty.data.models.location.LocationData

interface LocationsApi {

    /**
     * Get locations by filters.
     * @param page - Locations page.
     * @param type - Types of the location.
     * @param dimension - Dimensions of the location.
     * @return  -  Response from the server.
     */
    @GET("location/")
    suspend fun getLocations(
        @Query("page") page: Int,
        @Query("name") name: String?,
        @Query("type") type: String?,
        @Query("dimension") dimension: String?
    ): PagedResponse<LocationData>

    /**
     * Get locations by id.
     * @param ids - String with ids.
     * @return - Response from the server.
     */
    @GET("location/{ids}")
    suspend fun getLocationsByIds(
        @Path("ids") ids: String
    ): Response<List<LocationData>>


    /**
     * Get locations by filters.
     * @param type - Types of the location.
     * @param dimension - Dimensions of the location.
     * @return  -  Response from the server.
     */
    @GET("location/")
    suspend fun getLocationsWithFilters(
        @Query("name") name: String?,
        @Query("type") type: String?,
        @Query("dimension") dimension: String?
    ): Response<List<LocationData>>
}