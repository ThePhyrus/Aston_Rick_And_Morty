package roman.bannikov.aston_rick_and_morty.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
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
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list.GetAllLocationsUseCase
import roman.bannikov.aston_rick_and_morty.view.viewmodels.character.CharacterDetailsViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.character.CharacterFilterViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.character.CharacterListViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeDetailsViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeFilterViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeListViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationDetailsViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationFilterViewModelProvider
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationListViewModelProvider
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }
////////////////////////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideCharacterListViewModelProvider(
        getAllCharactersUseCase: GetAllCharactersUseCase
    ): CharacterListViewModelProvider {
        return CharacterListViewModelProvider(
            getAllCharactersUseCase = getAllCharactersUseCase
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

    @Provides
    @Singleton
    fun provideCharacterFilterViewModelProvider(
        getListCharactersTypesUseCase: GetListCharactersTypesUseCase,
        getListCharactersSpeciesUseCase: GetListCharactersSpeciesUseCase
    ): CharacterFilterViewModelProvider {
        return CharacterFilterViewModelProvider(
            getListCharactersTypesUseCase = getListCharactersTypesUseCase,
            getListCharactersSpeciesUseCase = getListCharactersSpeciesUseCase
        )
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideEpisodeListViewModelProvider(
        getAllEpisodesUseCase: GetAllEpisodesUseCase,
    ): EpisodeListViewModelProvider {
        return EpisodeListViewModelProvider(
            getAllEpisodesUseCase = getAllEpisodesUseCase,
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
    fun provideEpisodeFilterViewModelProvider(
        getListEpisodesUseCase: GetListEpisodesUseCase,
    ): EpisodeFilterViewModelProvider {
        return EpisodeFilterViewModelProvider(
            getListEpisodesUseCase = getListEpisodesUseCase,
        )
    }

////////////////////////////////////////////////////////////////////////////////////////////////////

    @Provides
    @Singleton
    fun provideLocationListViewModelProvider(
        getAllLocationsUseCase: GetAllLocationsUseCase
    ): LocationListViewModelProvider {
        return LocationListViewModelProvider(
            getAllLocationsUseCase = getAllLocationsUseCase
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
    fun provideLocationFilterViewModelProvider(
        getListLocationsDimensionsUseCase: GetListLocationsDimensionsUseCase,
        getListLocationsTypesUseCase: GetListLocationsTypesUseCase
    ): LocationFilterViewModelProvider {
        return LocationFilterViewModelProvider(
            getListLocationsDimensionsUseCase = getListLocationsDimensionsUseCase,
            getListLocationsTypesUseCase = getListLocationsTypesUseCase
        )
    }

////////////////////////////////////////////////////////////////////////////////////////////////////
}