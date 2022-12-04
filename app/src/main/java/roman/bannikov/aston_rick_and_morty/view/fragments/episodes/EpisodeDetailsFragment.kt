package roman.bannikov.aston_rick_and_morty.view.fragments.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodeDetailsBinding
import roman.bannikov.aston_rick_and_morty.view.navigator

import kotlin.properties.Delegates


class EpisodeDetailsFragment : Fragment() {

    private var episodeId by Delegates.notNull<Int>()

    companion object {
        private const val KEY_EPISODE_ID: String = "KEY_EPISODE_ID"

        @JvmStatic
        fun newInstance(
            episodeId: Int,
        ) =
            EpisodeDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_EPISODE_ID, episodeId)
                }
            }
    }

    private lateinit var binding: FragmentEpisodeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodeId = requireArguments().getInt(KEY_EPISODE_ID)
    }

    private fun openCharacterDetails(characterId: Int) {
        navigator().openCharacterDetailFragment(characterId = characterId)
    }

    private fun init() {
        arguments?.let {
            episodeId = it.getInt(KEY_EPISODE_ID)
        }
    }
}