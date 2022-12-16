package roman.bannikov.aston_rick_and_morty.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.utils.Navigator
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterListFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episode.EpisodeDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episode.EpisodeFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episode.EpisodeListFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.location.LocationDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.location.LocationFilterFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.location.LocationListFragment

//todo узнать, почему неизветно происхождение Морти (origin unknown)


@ExperimentalPagingApi
class MainActivity : AppCompatActivity(), Navigator {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                    launchCharacterListFragment()
                    true
                }
                R.id.locationsFragment -> {
                    launchLocationListFragment()
                    true
                }
                R.id.episodesFragment -> {
                    launchEpisodeListFragment()
                    true
                }
                else -> false
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    override fun onBackPressed() {
        val fragment1: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag("CHARACTERS_FRAGMENT") as CharacterListFragment?
        val fragment2: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag("OPEN_CharactersFragmentWithArg") as CharacterListFragment?
        val fragment3: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag("ADD FIRST FRAGMENT") as CharacterListFragment?
        val fragment4: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag("OPEN_CHARACTERS_FRAGMENT") as CharacterListFragment?
        if (fragment1 != null && fragment1.isVisible ||
            fragment2 != null && fragment2.isVisible ||
            fragment3 != null && fragment3.isVisible ||
            fragment4 != null && fragment4.isVisible
        ) {
            finish()
        }
        super.onBackPressed()
    }

    override fun goBack() {
        onBackPressed()
    }

    override fun launchCharacterListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                CharacterListFragment(),
                "CHARACTERS_FRAGMENT"
            ).addToBackStack("OPEN_CHARACTERS_FRAGMENT")
            .commit()
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun launchEpisodeListFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val backEntry: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val tag = backEntry.name
            if (tag == "OPEN_EPISODE_FRAGMENT") return
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodeListFragment(),
                "EPISODES_FRAGMENT"
            ).addToBackStack("OPEN_EPISODE_FRAGMENT")
            .commit()
    }

    override fun launchLocationListFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val backEntry: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount - 1)
            val tag = backEntry.name
            if (tag == "OPEN_LOCATION_FRAGMENT") return
        }

        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationListFragment(),
                "LOCATIONS_FRAGMENT"
            ).addToBackStack("OPEN_LOCATION_FRAGMENT")
            .commit()
    }

    override fun launchCharacterFilterFragment() {
        CharacterFilterFragment().show(supportFragmentManager, "CHARACTERS_FILTER_FRAGMENT")
    }

    override fun launchEpisodeFilterFragment() {
        EpisodeFilterFragment().show(supportFragmentManager, "EPISODES_FILTER_FRAGMENT")
    }

    override fun launchLocationFilterFragment() {

        LocationFilterFragment().show(supportFragmentManager, "LOCATIONS_FILTER_FRAGMENT")
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    override fun launchCharacterListFragmentWithArguments(
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
    override fun launchEpisodeListFragmentWithArguments(episode: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodeListFragment.newInstance(
                    episode = episode
                ),
                "EPISODES_FRAGMENT_ARG"
            ).addToBackStack("OPEN_EpisodesFragmentWithArg")
            .commit()
    }

    @OptIn(ExperimentalCoroutinesApi::class, kotlinx.coroutines.FlowPreview::class)
    override fun launchLocationListFragmentWithArguments(type: String?, dimension: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationListFragment.newInstance(
                    types = type,
                    dimensions = dimension,
                ),
                "LOCATIONS_FRAGMENT_ARG"
            ).addToBackStack("OPEN_LocationsFragmentWithArg")
            .commit()
    }

    override fun launchCharacterDetailsFragment(characterId: Int) {
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

    override fun launchEpisodeDetailsFragment(episodeId: Int) {
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

    override fun launchLocationDetailsFragment(locationId: Int) {
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