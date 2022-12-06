package roman.bannikov.aston_rick_and_morty.di

import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharacterDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.CharactersRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.characters_repositories.GetCharacterFiltersRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.EpisodeDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.EpisodesRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.episodes_repositories.GetEpisodeFiltersRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.GetLocationFiltersRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.LocationDetailsRepository
import roman.bannikov.aston_rick_and_morty.domain.repositories.locations_repositories.LocationsRepository
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_details_use_cases.GetCharacterByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersTypesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases.GetAllCharactersUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_details_use_cases.GetEpisodeByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_filters_use_cases.GetListEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases.GetAllEpisodesByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases.GetAllEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_details_use_cases.GetLocationByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_filters_use_cases.GetListLocationsDimensionsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_filters_use_cases.GetListLocationsTypesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.locations_usecases.GetAllLocationsByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.locations_usecases.GetAllLocationsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideGetAllLocationsUseCase(
        locationsRepository: LocationsRepository
    ): GetAllLocationsUseCase {
        return GetAllLocationsUseCase(
            locationsRepository = locationsRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetAllLocationsByIdsUseCase(
        locationsRepository: LocationsRepository
    ): GetAllLocationsByIdsUseCase {
        return GetAllLocationsByIdsUseCase(
            locationsRepository = locationsRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetListLocationsTypesUseCase(
        getLocationFiltersRepository: GetLocationFiltersRepository
    ): GetListLocationsTypesUseCase {
        return GetListLocationsTypesUseCase(
            getLocationFiltersRepository = getLocationFiltersRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetListLocationsDimensionsUseCase(
        getLocationFiltersRepository: GetLocationFiltersRepository
    ): GetListLocationsDimensionsUseCase {
        return GetListLocationsDimensionsUseCase(
            getLocationFiltersRepository = getLocationFiltersRepository
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


    @Provides
    @Singleton
    fun provideGetAllEpisodesUseCase(
        episodesRepository: EpisodesRepository
    ): GetAllEpisodesUseCase {
        return GetAllEpisodesUseCase(
            episodesRepository = episodesRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetAllEpisodesByIdsUseCase(
        episodesRepository: EpisodesRepository
    ): GetAllEpisodesByIdsUseCase {
        return GetAllEpisodesByIdsUseCase(
            episodesRepository = episodesRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetListEpisodesUseCase(
        getEpisodeFiltersRepository: GetEpisodeFiltersRepository
    ): GetListEpisodesUseCase {
        return GetListEpisodesUseCase(
            getEpisodeFiltersRepository = getEpisodeFiltersRepository
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



    @Provides
    @Singleton
    fun provideGetAllCharactersUseCase(
        charactersRepository: CharactersRepository
    ): GetAllCharactersUseCase {
        return GetAllCharactersUseCase(
            charactersRepository = charactersRepository
        )
    }



    @Provides
    @Singleton
    fun provideGetAllCharactersByIdsUseCase(
        charactersRepository: CharactersRepository
    ): GetAllCharactersByIdsUseCase {
        return GetAllCharactersByIdsUseCase(
            charactersRepository = charactersRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetListCharactersTypesUseCase(
        getCharacterFiltersRepository: GetCharacterFiltersRepository
    ): GetListCharactersTypesUseCase {
        return GetListCharactersTypesUseCase(
            getCharacterFiltersRepository = getCharacterFiltersRepository
        )
    }


    @Provides
    @Singleton
    fun provideGetListCharactersSpeciesUseCase(
        getCharacterFiltersRepository: GetCharacterFiltersRepository
    ): GetListCharactersSpeciesUseCase {
        return GetListCharactersSpeciesUseCase(
            getCharacterFiltersRepository = getCharacterFiltersRepository
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

}