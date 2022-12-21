package roman.bannikov.aston_rick_and_morty.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.bottomnavigation.BottomNavigationView
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

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var countDownTimer: CountDownTimer


    @OptIn(ExperimentalPagingApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        countDownTimer = object : CountDownTimer(2000L, 1000L) {
            override fun onTick(millisUntilFinished: Long) {
                //FIXME что с ним можно сделать?
            }

            override fun onFinish() {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}