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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodeDetailsBinding
import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.character.CharacterListForDetailsAdapter
import roman.bannikov.aston_rick_and_morty.viewmodel.episode.EpisodeDetailsViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.episode.EpisodeDetailsViewModelProvider
import kotlin.properties.Delegates


@ExperimentalPagingApi
class EpisodeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeDetailsBinding
    private lateinit var vm: EpisodeDetailsViewModel
    private var characterListForDetailsAdapter: CharacterListForDetailsAdapter? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(
            this,
            EpisodeDetailsViewModelProvider(requireContext())
        )[EpisodeDetailsViewModel::class.java]
        vm.getEpisode(episodeId)
        initView()
        observeVm()
    }

    private fun initView() {
        characterListForDetailsAdapter = CharacterListForDetailsAdapter()

        with(binding.rvEpisodeDetails) {
            layoutManager = LinearLayoutManager(requireContext())
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = characterListForDetailsAdapter
        }
        characterListForDetailsAdapter!!.onCharacterItem =
            { navigator().openCharacterDetailFragment(it.id) }
    }

    private fun observeVm() {
        lifecycle.coroutineScope.launch {
            vm.charactersList.observe(viewLifecycleOwner, Observer {
                characterListForDetailsAdapter!!.submitList(it)
            })
        }

        lifecycle.coroutineScope.launch {
            vm.episodeDetails.observe(viewLifecycleOwner, Observer {
                vm.getEpisodesList(it.residentsIds)
                initUI(it)
            })
        }
    }

    private fun initUI(presentationDetails: EpisodePresentation) {
        binding.tvEpisodeAirDateInEpisodeDetails.text = presentationDetails.air_date
        binding.tvEpisodeNameInEpisodeDetails.text = presentationDetails.name
        binding.tvEpisodeCodeInEpisodeDetails.text = presentationDetails.episode
    }

    private fun init() {
        arguments?.let {
            episodeId = it.getInt(KEY_EPISODE_ID)
        }
    }
}