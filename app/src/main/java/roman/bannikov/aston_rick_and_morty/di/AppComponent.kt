package roman.bannikov.aston_rick_and_morty.di

import androidx.paging.ExperimentalPagingApi
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterListFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episode.EpisodeDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episode.EpisodeFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episode.EpisodeListFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.location.LocationDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.location.LocationFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.location.LocationListFragment
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@ExperimentalPagingApi

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])

interface AppComponent {


    fun inject(characterListFragment: CharacterListFragment)
    fun inject(characterDetailsFragment: CharacterDetailsFragment)
    fun inject(characterFiltersFragment: CharacterFilterFragment)

    fun inject(episodeListFragment: EpisodeListFragment)
    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)
    fun inject(episodeFiltersFragment: EpisodeFilterFragment)

    fun inject(locationListFragment: LocationListFragment)
    fun inject(locationDetailsFragment: LocationDetailsFragment)
    fun inject(locationFiltersFragment: LocationFilterFragment)

}


