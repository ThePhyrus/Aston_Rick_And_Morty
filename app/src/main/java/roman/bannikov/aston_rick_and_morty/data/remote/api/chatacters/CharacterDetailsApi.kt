package roman.bannikov.aston_rick_and_morty.data.remote.api.chatacters

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import roman.bannikov.aston_rick_and_morty.data.models.character.CharacterData

interface CharacterDetailsApi {

    /**
     * Get character by id
     * @param id - Character id
     * @return  -  Response from the server
     */
    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): Response<CharacterData>
}