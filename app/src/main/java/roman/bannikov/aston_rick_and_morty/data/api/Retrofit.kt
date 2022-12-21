package roman.bannikov.aston_rick_and_morty.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import roman.bannikov.aston_rick_and_morty.data.api.chatacter.CharacterApi
import roman.bannikov.aston_rick_and_morty.data.api.chatacter.CharacterDetailsApi
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeApi
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeDetailsApi
import roman.bannikov.aston_rick_and_morty.data.api.location.LocationApi
import roman.bannikov.aston_rick_and_morty.data.api.location.LocationDetailsApi

object Retrofit {
    private const val BASE_URL = "https://rickandmortyapi.com/api/"
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val characterApi: CharacterApi by lazy {
        retrofit.create(CharacterApi::class.java)
    }

    val episodeApi: EpisodeApi by lazy {
        retrofit.create(EpisodeApi::class.java)
    }

    val locationApi: LocationApi by lazy {
        retrofit.create(LocationApi::class.java)
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