package roman.bannikov.aston_rick_and_morty.data.remote.api

import roman.bannikov.aston_rick_and_morty.data.remote.api.chatacters.CharacterDetailsApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.chatacters.CharactersApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.episodes.EpisodeDetailsApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.episodes.EpisodesApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.locations.LocationDetailsApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.locations.LocationsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val charactersApi: CharactersApi by lazy {
        retrofit.create(CharactersApi::class.java)
    }

    val episodesApi: EpisodesApi by lazy {
        retrofit.create(EpisodesApi::class.java)
    }

    val locationsApi: LocationsApi by lazy {
        retrofit.create(LocationsApi::class.java)
    }

    val characterDetailsApi: CharacterDetailsApi by lazy {
        retrofit.create(CharacterDetailsApi::class.java)
    }

    val episodeDetailsApi: EpisodeDetailsApi by lazy {
        retrofit.create(EpisodeDetailsApi::class.java)
    }

    val locationDetailsApi: LocationDetailsApi by lazy {
        retrofit.create(LocationDetailsApi::class.java)
    }
}