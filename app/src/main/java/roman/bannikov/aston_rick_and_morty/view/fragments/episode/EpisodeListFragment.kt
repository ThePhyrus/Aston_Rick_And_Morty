package roman.bannikov.aston_rick_and_morty.view.fragments.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodeListBinding
import roman.bannikov.aston_rick_and_morty.di.App
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.episode.EpisodeAdapter
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeListViewModel
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeListViewModelProvider
import javax.inject.Inject
import kotlin.collections.getValue
import kotlin.collections.set


@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class EpisodeListFragment : Fragment() {

    private var _binding: FragmentEpisodeListBinding? = null
    private val binding: FragmentEpisodeListBinding get() = _binding!!

    private var episodeAdapter: EpisodeAdapter = EpisodeAdapter()

    @Inject
    lateinit var episodeListViewModelProvider: EpisodeListViewModelProvider
    private lateinit var viewModel: EpisodeListViewModel

    private var episode: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            episode = it.getString(KEY_EPISODE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeListBinding.inflate(layoutInflater, container, false)

        binding.btnFilterEpisode.setOnClickListener {
            navigator().launchEpisodeFilterFragment()
        }

        initSearchView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as App).appComponent.inject(this)
        initViewModel()
        initRecyclerView()
        collectUiState()
        setUpSwipeToRefresh()
        observeViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            episodeListViewModelProvider
        )[EpisodeListViewModel::class.java]
        if (episode != null) viewModel.filteredTrigger.value[
                EpisodeListViewModel.MAP_KEY_EPISODE_CODE] = episode
    }

    private fun observeViewModel() {

        lifecycle.coroutineScope.launch {
            viewModel.filteredTrigger.collect {
                viewModel.getEpisodeByParams(
                    name = viewModel.filteredTrigger.value.getValue(
                        EpisodeListViewModel.MAP_KEY_EPISODE_NAME
                    ),
                    episode = viewModel.filteredTrigger.value.getValue(
                        EpisodeListViewModel.MAP_KEY_EPISODE_CODE
                    ),
                )
            }
        }

    }

    private fun initSearchView() {
        binding.svEpisode.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                performSearchEvent(query = newText)
                return false
            }

        })
    }
    private fun performSearchEvent(query: String) {
//        vm.getEpisodes(name = query, episode = episode) //fixme
    }

    private fun initRecyclerView() {
        with(binding.rvEpisodes) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = episodeAdapter
        }
        episodeAdapter.onEpisodeItem = { navigator().launchEpisodeDetailsFragment(it.id) }
    }

    private fun setUpSwipeToRefresh() {
        binding.srEpisodeList.apply {
            setOnRefreshListener {
                viewModel.getEpisodeByParams(null, null)
                binding.srEpisodeList.isRefreshing = false
                binding.rvEpisodes.scrollToPosition(0)
            }
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.episodesFlow.collectLatest { episodeAdapter.submitData(it) }
        }
    }

    companion object {
        private const val KEY_EPISODE: String = "KEY_EPISODE"

        @JvmStatic
        fun newInstance(
            episode: String?,
        ) =
            EpisodeListFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_EPISODE, episode)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}