package roman.bannikov.aston_rick_and_morty.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
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
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.locations_usecases.GetAllLocationsUseCase
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.character_details_fragment.CharacterDetailsViewModelProvider
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_filter_fragment.CharacterFiltersViewModelProvider
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_fragment.CharactersViewModelProvider
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episode_details_fragment.EpisodeDetailsViewModelProvider
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_filter_fragment.EpisodeFilterViewModelProvider
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_fragment.EpisodesViewModelProvider
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.location_details_fragment.LocationDetailsViewModelProvider
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.locations_filter_fragment.LocationFiltersViewModelProvider
import com.example.rickandmorty.presentation.screens.locations.locations_fragment.LocationsViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context{
        return context
    }


    @Provides
    @Singleton
    fun provideLocationsViewModelProvider(
        getAllLocationsUseCase: GetAllLocationsUseCase
    ): LocationsViewModelProvider {
        return LocationsViewModelProvider(
            getAllLocationsUseCase = getAllLocationsUseCase
        )
    }

    @Provides
    @Singleton
    fun provideLocationFiltersViewModelProvider(
        getListLocationsDimensionsUseCase: GetListLocationsDimensionsUseCase,
        getListLocationsTypesUseCase: GetListLocationsTypesUseCase
    ): LocationFiltersViewModelProvider {
        return LocationFiltersViewModelProvider(
            getListLocationsDimensionsUseCase = getListLocationsDimensionsUseCase,
            getListLocationsTypesUseCase = getListLocationsTypesUseCase
        )
    }


    @Provides
    @Singleton
    fun provideLocationDetailsViewModelProvider(
        getLocationByIdUseCase: GetLocationByIdUseCase,
        getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
    ): LocationDetailsViewModelProvider {
        return LocationDetailsViewModelProvider(
            getLocationByIdUseCase = getLocationByIdUseCase,
            getAllCharactersByIdsUseCase = getAllCharactersByIdsUseCase
        )
    }


    @Provides
    @Singleton
    fun provideEpisodesViewModelProvider(
        getAllEpisodesUseCase: GetAllEpisodesUseCase,
    ): EpisodesViewModelProvider {
        return EpisodesViewModelProvider(
            getAllEpisodesUseCase = getAllEpisodesUseCase,
        )
    }


    @Provides
    @Singleton
    fun provideEpisodeFilterViewModelProvider(
        getListEpisodesUseCase: GetListEpisodesUseCase,
    ): EpisodeFilterViewModelProvider {
        return EpisodeFilterViewModelProvider(
            getListEpisodesUseCase = getListEpisodesUseCase,
        )
    }


    @Provides
    @Singleton
    fun provideEpisodeDetailsViewModelProvider(
        getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
        getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
    ): EpisodeDetailsViewModelProvider {
        return EpisodeDetailsViewModelProvider(
            getEpisodeByIdUseCase = getEpisodeByIdUseCase,
            getAllCharactersByIdsUseCase = getAllCharactersByIdsUseCase
        )
    }

    @Provides
    @Singleton
    fun provideCharactersViewModelProvider(
        getAllCharactersUseCase: GetAllCharactersUseCase
    ): CharactersViewModelProvider {
        return CharactersViewModelProvider(
            getAllCharactersUseCase = getAllCharactersUseCase
        )
    }


    @Provides
    @Singleton
    fun provideCharacterFiltersViewModelProvider(
        getListCharactersTypesUseCase: GetListCharactersTypesUseCase,
        getListCharactersSpeciesUseCase: GetListCharactersSpeciesUseCase
    ): CharacterFiltersViewModelProvider {
        return CharacterFiltersViewModelProvider(
            getListCharactersTypesUseCase = getListCharactersTypesUseCase,
            getListCharactersSpeciesUseCase = getListCharactersSpeciesUseCase
        )
    }


    @Provides
    @Singleton
    fun provideCharacterDetailsViewModelProvider(
        getCharacterByIdUseCase: GetCharacterByIdUseCase,
        getAllEpisodesByIdsUseCase: GetAllEpisodesByIdsUseCase
    ): CharacterDetailsViewModelProvider {
        return CharacterDetailsViewModelProvider(
            getCharacterByIdUseCase = getCharacterByIdUseCase,
            getAllEpisodesByIdsUseCase = getAllEpisodesByIdsUseCase
        )
    }

}