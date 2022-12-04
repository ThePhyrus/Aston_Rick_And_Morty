package roman.bannikov.aston_rick_and_morty.view.fragments.episodes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodesBinding
import roman.bannikov.aston_rick_and_morty.view.navigator


class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private var episode: String? = null

    companion object {
        private const val KEY_EPISODE: String = "KEY_EPISODE"

        @JvmStatic
        fun newInstance(
            episode: String?,
        ) =
            EpisodesFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_EPISODE, episode)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFilterEpisodes.setOnClickListener {
            navigator().openEpisodesFilterFragment()
        }

        binding.episodesLabel.setOnClickListener {
            Toast.makeText(requireContext(), "$episode", Toast.LENGTH_SHORT).show()

        }

    }

    private fun init() {
        arguments?.let {
            episode = it.getString(KEY_EPISODE)
        }
    }
}