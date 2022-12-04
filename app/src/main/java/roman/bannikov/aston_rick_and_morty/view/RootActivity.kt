package roman.bannikov.aston_rick_and_morty.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.google.android.material.bottomnavigation.BottomNavigationView
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.view.fragments.characters.CharacterDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.characters.CharactersFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.characters.CharactersFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episodes.EpisodeDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episodes.EpisodesFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episodes.EpisodesFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.locations.LocationDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.locations.LocationFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.locations.LocationsFragment

class RootActivity : AppCompatActivity(), Navigator {

    private val vm: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                vm.isLoading.value
            }
        }
        setContentView(R.layout.activity_root)

        val manager = supportFragmentManager
        if (manager.backStackEntryCount == 0) {
            openCharactersFragment()
        } else {
            val backEntry = manager.getBackStackEntryAt(manager.backStackEntryCount - 1)

            val tag = backEntry.name
            manager.popBackStack(tag, 0)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.charactersFragment -> {
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

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }

    override fun openCharactersFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_navigator,
                CharactersFragment(),
                "CHARACTERS_FRAGMENT"
            ).addToBackStack("OPEN_CHARACTERS_FRAGMENT")
            .commit()
    }

    override fun openEpisodesFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_navigator,
                EpisodesFragment(),
                "EPISODES_FRAGMENT"
            ).addToBackStack("OPEN_EPISODE_FRAGMENT")
            .commit()
    }

    override fun openLocationsFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_navigator,
                LocationsFragment(),
                "LOCATIONS_FRAGMENT"
            ).addToBackStack("OPEN_LOCATION_FRAGMENT")
            .commit()
    }

    override fun openCharactersFilterFragment() {
        CharactersFilterFragment().show(supportFragmentManager, "CHARACTERS_FILTER_FRAGMENT")
    }

    override fun openEpisodesFilterFragment() {
        EpisodesFilterFragment().show(supportFragmentManager, "EPISODES_FILTER_FRAGMENT")
    }

    override fun openLocationsFilterFragment() {

        LocationFilterFragment().show(supportFragmentManager, "LOCATIONS_FILTER_FRAGMENT")
    }

    override fun openCharactersFragmentWithArg(
        status: String?,
        gender: String?,
        species: String?,
        type: String?
    ) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_navigator,
                CharactersFragment.newInstance(
                    gender = gender,
                    status = status,
                    species = species,
                    type = type
                ),
                "CHARACTERS_FRAGMENT_ARG"
            ).addToBackStack("OPEN_CharactersFragmentWithArg")
            .commit()
    }

    override fun openEpisodesFragmentWithArg(episode: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_navigator,
                EpisodesFragment.newInstance(
                    episode = episode
                ),
                "EPISODES_FRAGMENT_ARG"
            ).addToBackStack("OPEN_EpisodesFragmentWithArg")
            .commit()
    }

    override fun openLocationsFragmentWithArg(type: String?, dimension: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container_navigator,
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
                R.id.container_navigator,
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
                R.id.container_navigator,
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
                R.id.container_navigator,
                LocationDetailsFragment.newInstance(
                    locationId = locationId
                ),
                "LOCATIONS_DETAIL_FRAGMENT"
            ).addToBackStack("OPEN_LocationsDetailFragment")
            .commit()
    }
}