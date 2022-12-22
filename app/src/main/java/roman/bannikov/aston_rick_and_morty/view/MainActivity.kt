package roman.bannikov.aston_rick_and_morty.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_CHARACTER_DETAILS_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_CHARACTER_LIST_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_EPISODE_DETAILS_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_EPISODE_LIST_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_FILTERED_CHARACTER_LIST
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_FILTERED_EPISODE_LIST
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_FILTERED_LOCATION_LIST
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_LOCATION_DETAILS_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.NAME_LOCATION_LIST_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_CHARACTER_DETAILS_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_CHARACTER_FILTER_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_CHARACTER_LIST_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_EPISODE_DETAILS_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_EPISODE_FILTER_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_EPISODE_LIST_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_FILTERED_CHARACTER_LIST
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_FILTERED_EPISODE_LIST
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_FILTERED_LOCATION_LIST
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_LOCATION_DETAILS_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_LOCATION_FILTER_FRAGMENT
import roman.bannikov.aston_rick_and_morty.utils.Const.Companion.TAG_LOCATION_LIST_FRAGMENT
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


@ExperimentalPagingApi
class MainActivity : AppCompatActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFirstScreen(savedInstanceState)
        initBottomNavigationView()
    }

    //initialize
    @OptIn(ExperimentalCoroutinesApi::class)
    private fun initFirstScreen(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.containerForFragment,
                    CharacterListFragment(),
                    TAG_CHARACTER_LIST_FRAGMENT
                ).commit()
        } else {
            if (supportFragmentManager.backStackEntryCount == 0) {
                supportFragmentManager.popBackStack(
                    NAME_CHARACTER_LIST_FRAGMENT,
                    0
                )
            } else {
                val backEntry: FragmentManager.BackStackEntry =
                    supportFragmentManager.getBackStackEntryAt(
                        supportFragmentManager.backStackEntryCount - 1
                    )
                val name = backEntry.name
                supportFragmentManager.popBackStack(name, 0)
            }
        }
    }

    private fun initBottomNavigationView() {
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

    //character
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun launchCharacterListFragment() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                CharacterListFragment(),
                TAG_CHARACTER_LIST_FRAGMENT
            ).addToBackStack(NAME_CHARACTER_LIST_FRAGMENT)
            .commit()
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun launchCharacterDetailsFragment(characterId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                CharacterDetailsFragment.newInstance(
                    characterId = characterId
                ),
                TAG_CHARACTER_DETAILS_FRAGMENT
            ).addToBackStack(NAME_CHARACTER_DETAILS_FRAGMENT)
            .commit()
    }

    override fun launchCharacterFilterFragment() {
        CharacterFilterFragment().show(supportFragmentManager, TAG_CHARACTER_FILTER_FRAGMENT)
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun launchFilteredCharacterListFragment(
        status: String?, gender: String?, species: String?, type: String?
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
                TAG_FILTERED_CHARACTER_LIST
            ).addToBackStack(NAME_FILTERED_CHARACTER_LIST)
            .commit()
    }

    //episode
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun launchEpisodeListFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val backEntry: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(
                    supportFragmentManager.backStackEntryCount - 1
                )
            val tag = backEntry.name
            if (tag == NAME_EPISODE_LIST_FRAGMENT) return
        }
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodeListFragment(),
                TAG_EPISODE_LIST_FRAGMENT
            ).addToBackStack(NAME_EPISODE_LIST_FRAGMENT)
            .commit()
    }

    override fun launchEpisodeDetailsFragment(episodeId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodeDetailsFragment.newInstance(
                    episodeId = episodeId
                ),
                TAG_EPISODE_DETAILS_FRAGMENT
            ).addToBackStack(NAME_EPISODE_DETAILS_FRAGMENT)
            .commit()
    }

    override fun launchEpisodeFilterFragment() {
        EpisodeFilterFragment().show(supportFragmentManager, TAG_EPISODE_FILTER_FRAGMENT)
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun launchFilteredEpisodeListFragment(episode: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                EpisodeListFragment.newInstance(
                    episode = episode
                ),
                TAG_FILTERED_EPISODE_LIST
            ).addToBackStack(NAME_FILTERED_EPISODE_LIST)
            .commit()
    }

    //location
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun launchLocationListFragment() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            val backEntry: FragmentManager.BackStackEntry =
                supportFragmentManager.getBackStackEntryAt(
                    supportFragmentManager.backStackEntryCount - 1
                )
            val name = backEntry.name
            if (name == NAME_LOCATION_LIST_FRAGMENT) return
        }
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationListFragment(),
                TAG_LOCATION_LIST_FRAGMENT
            ).addToBackStack(NAME_LOCATION_LIST_FRAGMENT)
            .commit()
    }

    override fun launchLocationDetailsFragment(locationId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationDetailsFragment.newInstance(
                    locationId = locationId
                ),
                TAG_LOCATION_DETAILS_FRAGMENT
            ).addToBackStack(NAME_LOCATION_DETAILS_FRAGMENT)
            .commit()
    }

    override fun launchLocationFilterFragment() {
        LocationFilterFragment().show(supportFragmentManager, TAG_LOCATION_FILTER_FRAGMENT)
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun launchFilteredLocationListFragment(type: String?, dimension: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.containerForFragment,
                LocationListFragment.newInstance(
                    types = type,
                    dimensions = dimension,
                ),
                TAG_FILTERED_LOCATION_LIST
            ).addToBackStack(NAME_FILTERED_LOCATION_LIST)
            .commit()
    }

    //go back
    @Deprecated("Deprecated in Java")
    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    override fun onBackPressed() {
        val fragment1: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag(TAG_CHARACTER_LIST_FRAGMENT)
                    as CharacterListFragment?
        val fragment2: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag(TAG_FILTERED_CHARACTER_LIST)
                    as CharacterListFragment?
        val fragment4: CharacterListFragment? =
            supportFragmentManager.findFragmentByTag(NAME_CHARACTER_LIST_FRAGMENT)
                    as CharacterListFragment?
        if (fragment1 != null && fragment1.isVisible ||
            fragment2 != null && fragment2.isVisible ||
            fragment4 != null && fragment4.isVisible
        ) {
            finish()
        }
        super.onBackPressed()
    }

    override fun goBack() {
        onBackPressed()
    }

}