package roman.bannikov.aston_rick_and_morty.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import roman.bannikov.aston_rick_and_morty.App
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.ActivityMainBinding
import roman.bannikov.aston_rick_and_morty.models.CharacterModelService
import roman.bannikov.aston_rick_and_morty.view.fragments.CharactersMainFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.EpisodesMainFragment
import roman.bannikov.aston_rick_and_morty.view.fragments.LocationsMainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        if (savedInstanceState == null) {
            val fragment = CharactersMainFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.containerForFragments, fragment).commit()
        }

        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottomMenuCharacters -> {
                    //launch fragment
                    supportFragmentManager.beginTransaction().replace(R.id.containerForFragments, CharactersMainFragment()).commit()
                }
                R.id.bottomMenuEpisodes -> {
                    supportFragmentManager.beginTransaction().replace(R.id.containerForFragments, EpisodesMainFragment()).commit()
                }
                R.id.bottomMenuLocations -> {
                    //launch fragment
                    supportFragmentManager.beginTransaction().replace(R.id.containerForFragments, LocationsMainFragment()).commit()
                }
            }

            true
        }
    }
}