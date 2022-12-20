package roman.bannikov.aston_rick_and_morty.view.fragments.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodeDetailsBinding
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.character.CharacterListForDetailsAdapter
import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeDetailsViewModel
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeDetailsViewModelProvider
import kotlin.properties.Delegates


@ExperimentalPagingApi
class EpisodeDetailsFragment : Fragment() {

    private var _binding: FragmentEpisodeDetailsBinding? = null
    private val binding: FragmentEpisodeDetailsBinding get() = _binding!!

    private lateinit var viewModel: EpisodeDetailsViewModel

    private var characterListForDetailsAdapter: CharacterListForDetailsAdapter? = null

    private var episodeId by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            episodeId = it.getInt(KEY_EPISODE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeDetailsBinding.inflate(layoutInflater, container, false)
        binding.btnBack.setOnClickListener {
            navigator().goBack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observeViewModel()
        initView()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            EpisodeDetailsViewModelProvider(requireContext())
        )[EpisodeDetailsViewModel::class.java]
        viewModel.getEpisode(episodeId)
    }

    private fun initView() {
        characterListForDetailsAdapter = CharacterListForDetailsAdapter()

        with(binding.rvEpisodeDetails) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = characterListForDetailsAdapter
        }
        characterListForDetailsAdapter!!.onCharacterItem =
            { navigator().launchCharacterDetailsFragment(it.id) }
    }

    private fun observeViewModel() {
        lifecycle.coroutineScope.launch {
            viewModel.charactersList.observe(viewLifecycleOwner, Observer {
                characterListForDetailsAdapter!!.submitList(it)
            })
        }

        lifecycle.coroutineScope.launch {
            viewModel.episodeDetails.observe(viewLifecycleOwner, Observer {
                viewModel.getEpisodesList(it.residentsIds)
                initUI(it)
            })
        }
    }

    private fun initUI(presentationDetails: EpisodeView) {
        binding.tvEpisodeAirDateInEpisodeDetails.text = presentationDetails.air_date
        binding.tvEpisodeNameInEpisodeDetails.text = presentationDetails.name
        binding.tvEpisodeCodeInEpisodeDetails.text = presentationDetails.episode
    }


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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}