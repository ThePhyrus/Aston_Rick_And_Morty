package roman.bannikov.aston_rick_and_morty.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.ActivityMainBinding
import roman.bannikov.aston_rick_and_morty.models.CharacterModel
import roman.bannikov.aston_rick_and_morty.utils.Constants.Companion.TAG_CHARACTER_DETAILS_FRAGMENT
import roman.bannikov.aston_rick_and_morty.view.fragments.Navigator
import roman.bannikov.aston_rick_and_morty.view.fragments.characters.CharacterDetailsFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.characters.CharactersMainFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.episodes.EpisodesMainFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.locations.LocationsMainFragment

//todo change image_for_item_episodes (drawable)
//fixme tvDataSapience?!? (CharacterDetailsFragment)

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.containerForFragments, CharactersMainFragment())
                .commit()
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomMenuCharacters -> {
                    //launch fragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.containerForFragments, CharactersMainFragment()).commit()
                }
                R.id.bottomMenuEpisodes -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.containerForFragments, EpisodesMainFragment()).commit()
                }
                R.id.bottomMenuLocations -> {
                    //launch fragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.containerForFragments, LocationsMainFragment()).commit()
                }
            }

            true
        }
    }

    //From Navigator()
    override fun showCharacterDetails(characterModel: CharacterModel) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(TAG_CHARACTER_DETAILS_FRAGMENT)
            .replace(
                R.id.containerForFragments,
                CharacterDetailsFragment.newInstance(characterId = characterModel.characterId)
            )
            .commit()
    }

    //From Navigator()
    override fun goBack() {
        onBackPressed()
//        onBackPressedDispatcher
    }

    //From Navigator()
    override fun showToast(messageRes: Int) {
        Toast.makeText(this, messageRes, Toast.LENGTH_LONG).show()
    }
}