package roman.bannikov.aston_rick_and_morty.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentManager
import androidx.paging.ExperimentalPagingApi

import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterListFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episode_details_fragment.EpisodeDetailsFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_filter_fragment.EpisodeFiltersFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_fragment.EpisodesFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.location_details_fragment.LocationDetailsFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.locations_filter_fragment.LocationFiltersFragment
import roman.bannikov.aston_rick_and_morty.presentation.screens.locations.locations_fragment.LocationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.presentation.Navigator
import roman.bannikov.aston_rick_and_morty.viewmodel.SplashScreenViewModel

//todo узнать, почему неизветно происхождение Морти (origin unknown)



@ExperimentalPagingApi
class MainActivity : AppCompatActivity(), Navigator {

    private val vm: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                vm.isLoading.value
            }
        }
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.containerForFragment,
                    CharacterListFragment(),
                    "ADD FIRST FRAGMENT"
                ).commit()
        } else {
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportFragmentManager.popBackStack("ADD FIRST FRAGMENT", 0)
            } else {
                val backEntry: FragmentManager.BackStackEntry =
                    supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
                val tag = backEntry.name
                supportFragmentManager.popBackStack(tag, 0)
            }
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.characterListFragment -> {
                    openCharactersFragment()
                    true
                }
                R.id.locationsFragment -> {
                    openLocationsFragment()
                    true
                }
                R.id.episodesFragment -> {
                    openEpisodesFragment()
                    true
                }
                else -> false
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    override fun onBackPressed() {
        super.onBackPressed()
        val fragment1: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag("CHARACTERS_FRAGMENT") as CharacterListFragment?
        val fragment2: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag("OPEN_CharactersFragmentWithArg") as CharacterListFragment?
        val fragment3: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag("ADD FIRST FRAGMENT") as CharacterListFragment?
        if (fragment1 != null && fragment1.isVisible ||
            fragment2 != null && fragment2.isVisible ||
            fragment3 != null && fragment3.isVisible ) {
            finish()
        }
    }

    override fun openCharactersFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                CharacterListFragment(),
                "CHARACTERS_FRAGMENT"
            ).addToBackStack("OPEN_CHARACTERS_FRAGMENT")
            .commit()
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun openEpisodesFragment() {
        if ( supportFragmentManager.backStackEntryCount > 0 ) {
            val backEntry: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val tag = backEntry.name
            if(tag == "OPEN_EPISODE_FRAGMENT") return
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodesFragment(),
                "EPISODES_FRAGMENT"
            ).addToBackStack("OPEN_EPISODE_FRAGMENT")
            .commit()
    }

    override fun openLocationsFragment() {
        if ( supportFragmentManager.backStackEntryCount > 0 ) {
            val backEntry: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val tag = backEntry.name
            if(tag == "OPEN_LOCATION_FRAGMENT") return
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationsFragment(),
                "LOCATIONS_FRAGMENT"
            ).addToBackStack("OPEN_LOCATION_FRAGMENT")
            .commit()
    }

    override fun openCharactersFilterFragment() {
        CharacterFilterFragment().show(supportFragmentManager, "CHARACTERS_FILTER_FRAGMENT")
    }

    override fun openEpisodesFilterFragment() {
        EpisodeFiltersFragment().show(supportFragmentManager, "EPISODES_FILTER_FRAGMENT")
    }

    override fun openLocationsFilterFragment() {

        LocationFiltersFragment().show(supportFragmentManager, "LOCATIONS_FILTER_FRAGMENT")
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    override fun openCharactersFragmentWithArg(
        status: String?,
        gender: String?,
        species: String?,
        type: String?
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                CharacterListFragment.newInstance(
                    gender = gender,
                    status = status,
                    species = species,
                    type = type
                ),
                "CHARACTERS_FRAGMENT_ARG"
            ).addToBackStack("OPEN_CharactersFragmentWithArg")
            .commit()
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    override fun openEpisodesFragmentWithArg(episode: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodesFragment.newInstance(
                    episode = episode
                ),
                "EPISODES_FRAGMENT_ARG"
            ).addToBackStack("OPEN_EpisodesFragmentWithArg")
            .commit()
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    override fun openLocationsFragmentWithArg(type: String?, dimension: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationsFragment.newInstance(
                    types = type,
                    dimensions = dimension,
                ),
                "LOCATIONS_FRAGMENT_ARG"
            ).addToBackStack("OPEN_LocationsFragmentWithArg")
            .commit()
    }

    override fun openCharacterDetailFragment(characterId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                CharacterDetailsFragment.newInstance(
                    characterId = characterId
                ),
                "CHARACTERS_DETAIL_FRAGMENT"
            ).addToBackStack("OPEN_CharacterDetailFragment")
            .commit()
    }

    override fun openEpisodesDetailFragment(episodeId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodeDetailsFragment.newInstance(
                    episodeId = episodeId
                ),
                "EPISODES_DETAIL_FRAGMENT"
            ).addToBackStack("OPEN_EpisodesDetailFragment")
            .commit()
    }

    override fun openLocationsDetailFragment(locationId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationDetailsFragment.newInstance(
                    locationId = locationId
                ),
                "LOCATIONS_DETAIL_FRAGMENT"
            ).addToBackStack("OPEN_LocationsDetailFragment")
            .commit()
    }
}