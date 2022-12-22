package roman.bannikov.aston_rick_and_morty.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import roman.bannikov.aston_rick_and_morty.data.api.chatacter.CharacterApi
import roman.bannikov.aston_rick_and_morty.data.api.chatacter.CharacterDetailsApi
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeApi
import roman.bannikov.aston_rick_and_morty.data.api.episode.EpisodeDetailsApi
import roman.bannikov.aston_rick_and_morty.data.api.location.LocationApi
import roman.bannikov.aston_rick_and_morty.data.api.location.LocationDetailsApi
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.GetCharacterFilterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeFilterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationFilterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationRepository
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.BASE_URL
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.DATABASE_NAME
import javax.inject.Singleton


@ExperimentalPagingApi
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    //  CHARACTER  /////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideCharacterRepository(
        database: AppDatabase,
        characterDetailsApi: CharacterDetailsApi,
        characterApi: CharacterApi,
    ): CharacterRepository {
        return CharacterRepositoryImpl(
            characterDetailsApi = characterDetailsApi,
            characterApi = characterApi,
            database = database
        )
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsRepository(
        database: AppDatabase,
        characterDetailsApi: CharacterDetailsApi
    ): CharacterDetailsRepository {
        return CharacterDetailsRepositoryImpl(
            characterDetailsApi = characterDetailsApi,
            database = database
        )
    }

    @Provides
    @Singleton
    fun provideGetCharacterFilterRepository(
        database: AppDatabase
    ): GetCharacterFilterRepository {
        return CharacterFilterRepositoryImpl(database = database)
    }


    @Provides
    @Singleton
    fun provideCharactersApi(): CharacterApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailsApi(): CharacterDetailsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharacterDetailsApi::class.java)
    }

    //  EPISODE  ///////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideEpisodeRepository(
        database: AppDatabase,
        episodeApi: EpisodeApi,
        episodeDetailsApi: EpisodeDetailsApi
    ): EpisodeRepository {
        return EpisodeRepositoryImpl(
            episodeApi = episodeApi,
            episodeDetailsApi = episodeDetailsApi,
            database = database
        )
    }

    @Provides
    @Singleton
    fun provideEpisodeDetailsRepository(
        database: AppDatabase,
        episodeDetailsApi: EpisodeDetailsApi
    ): EpisodeDetailsRepository {
        return EpisodeDetailsRepositoryImpl(
            episodeDetailsApi = episodeDetailsApi,
            database = database
        )
    }

    @Provides
    @Singleton
    fun provideGetEpisodeFilterRepository(
        database: AppDatabase,
    ): EpisodeFilterRepository {
        return EpisodeFilterRepositoryImpl(
            database = database
        )
    }

    @Provides
    @Singleton
    fun provideEpisodeApi(): EpisodeApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EpisodeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideEpisodeDetailsApi(): EpisodeDetailsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EpisodeDetailsApi::class.java)
    }

    //  LOCATION  //////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideLocationRepository(
        database: AppDatabase,
        locationApi: LocationApi
    ): LocationRepository {
        return LocationRepositoryImpl(
            database = database,
            locationApi = locationApi
        )
    }

    @Provides
    @Singleton
    fun provideLocationDetailsRepository(
        database: AppDatabase,
        locationDetailsApi: LocationDetailsApi
    ): LocationDetailsRepository {
        return LocationDetailsRepositoryImpl(
            locationDetailsApi = locationDetailsApi,
            database = database
        )
    }

    @Provides
    @Singleton
    fun provideGetLocationFiltersRepository(
        database: AppDatabase
    ): LocationFilterRepository {
        return LocationFilterRepositoryImpl(
            database = database
        )
    }

    @Provides
    @Singleton
    fun provideLocationDetailsApi(): LocationDetailsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocationsApi(): LocationApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationApi::class.java)
    }

}