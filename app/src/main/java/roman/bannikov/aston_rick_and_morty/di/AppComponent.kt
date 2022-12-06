package roman.bannikov.aston_rick_and_morty.di

import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.character_details_fragment.CharacterDetailsFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_filter_fragment.CharacterFiltersFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_fragment.CharactersFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episode_details_fragment.EpisodeDetailsFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_filter_fragment.EpisodeFiltersFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_fragment.EpisodesFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.location_details_fragment.LocationDetailsFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.locations_filter_fragment.LocationFiltersFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.locations_fragment.LocationsFragment
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@ExperimentalPagingApi

@Singleton
@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(characterDetailsFragment: CharacterDetailsFragment)
    fun inject(characterFiltersFragment: CharacterFiltersFragment)
    fun inject(charactersFragment: CharactersFragment)

    fun inject(episodeDetailsFragment: EpisodeDetailsFragment)
    fun inject(episodeFiltersFragment: EpisodeFiltersFragment)
    fun inject(episodesFragment: EpisodesFragment)

    fun inject(locationDetailsFragment: LocationDetailsFragment)
    fun inject(locationFiltersFragment: LocationFiltersFragment)
    fun inject(locationsFragment: LocationsFragment)
}