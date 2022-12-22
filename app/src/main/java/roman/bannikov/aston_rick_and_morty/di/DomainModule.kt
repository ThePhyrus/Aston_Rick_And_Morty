package roman.bannikov.aston_rick_and_morty.di

import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.CharacterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.character.GetCharacterFilterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeFilterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episode.EpisodeRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationFilterRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.location.LocationRepository
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.details.GetCharacterByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersTypesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.details.GetEpisodeByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.filter.GetListEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list.GetAllEpisodesByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list.GetAllEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.details.GetLocationByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsDimensionsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsTypesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list.GetAllLocationsByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list.GetAllLocationsUseCase
import javax.inject.Singleton


@ExperimentalPagingApi
@Module
class DomainModule {
    /////////////////CHARACTER/////////////////////////////////

    @Provides
    @Singleton
    fun provideGetAllCharactersUseCase(
        characterRepository: CharacterRepository
    ): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(
            characterRepository = characterRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetAllCharactersByIdsUseCase(
        characterRepository: CharacterRepository
    ): GetAllCharactersByIdsUseCase {
        return GetAllCharactersByIdsUseCase(
            characterRepository = characterRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetListCharactersTypesUseCase(
        getCharacterFilterRepository: GetCharacterFilterRepository
    ): GetListCharactersTypesUseCase {
        return GetListCharactersTypesUseCase(
            getCharacterFilterRepository = getCharacterFilterRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetListCharactersSpeciesUseCase(
        getCharacterFilterRepository: GetCharacterFilterRepository
    ): GetListCharactersSpeciesUseCase {
        return GetListCharactersSpeciesUseCase(
            getCharacterFilterRepository = getCharacterFilterRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetCharacterByIdUseCase(
        characterDetailsRepository: CharacterDetailsRepository
    ): GetCharacterByIdUseCase {
        return GetCharacterByIdUseCase(
            characterDetailsRepository = characterDetailsRepository
        )
    }


    /////////////////EPISODE////////////////////////////////

    @Provides
    @Singleton
    fun provideGetAllEpisodesUseCase(
        episodeRepository: EpisodeRepository
    ): GetAllEpisodesUseCase {
        return GetAllEpisodesUseCase(
            episodeRepository = episodeRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetAllEpisodesByIdsUseCase(
        episodeRepository: EpisodeRepository
    ): GetAllEpisodesByIdsUseCase {
        return GetAllEpisodesByIdsUseCase(
            episodeRepository = episodeRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetListEpisodesUseCase(
        episodeFilterRepository: EpisodeFilterRepository
    ): GetListEpisodesUseCase {
        return GetListEpisodesUseCase(
            episodeFilterRepository = episodeFilterRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetEpisodeByIdUseCase(
        episodeDetailsRepository: EpisodeDetailsRepository
    ): GetEpisodeByIdUseCase {
        return GetEpisodeByIdUseCase(
            episodeDetailsRepository = episodeDetailsRepository
        )
    }























    /////////////////LOCATION/////////////////////////////////

    @Provides
    @Singleton
    fun provideGetAllLocationsUseCase(
        locationRepository: LocationRepository
    ): GetAllLocationsUseCase {
        return GetAllLocationsUseCase(
            locationRepository = locationRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetAllLocationsByIdsUseCase(
        locationRepository: LocationRepository
    ): GetAllLocationsByIdsUseCase {
        return GetAllLocationsByIdsUseCase(
            locationRepository = locationRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetListLocationsTypesUseCase(
        locationFilterRepository: LocationFilterRepository
    ): GetListLocationsTypesUseCase {
        return GetListLocationsTypesUseCase(
            locationFilterRepository = locationFilterRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetListLocationsDimensionsUseCase(
        locationFilterRepository: LocationFilterRepository
    ): GetListLocationsDimensionsUseCase {
        return GetListLocationsDimensionsUseCase(
            locationFilterRepository = locationFilterRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetLocationByIdUseCase(
        locationDetailsRepository: LocationDetailsRepository
    ): GetLocationByIdUseCase {
        return GetLocationByIdUseCase(
            locationDetailsRepository = locationDetailsRepository
        )
    }

}