package roman.bannikov.aston_rick_and_morty.view.fragments.episode

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodeListBinding
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.episode.EpisodeAdapter
import roman.bannikov.aston_rick_and_morty.viewmodel.episode.EpisodeListViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.episode.EpisodeListViewModelProvider


@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class EpisodeListFragment : Fragment() {

    private lateinit var binding: FragmentEpisodeListBinding
    private var episodeAdapter: EpisodeAdapter = EpisodeAdapter()

    private var episode: String? = null

    private lateinit var vm: EpisodeListViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(
            this,
            EpisodeListViewModelProvider(requireContext())
        )[EpisodeListViewModel::class.java]

        initRecyclerView()
        collectUiState()

        if (episode != null) vm.filteredTrigger.value["episode"] = episode

        setUpSwipeToRefresh()

        binding.btnFilterEpisode.setOnClickListener {
            navigator().openEpisodesFilterFragment()
        }

        binding.svEpisode.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                performSearchEvent(query = newText)
                return false
            }

        })
        observeVM()
    }

    private fun observeVM() {

        lifecycle.coroutineScope.launch {
            vm.filteredTrigger.collect {
                vm.getEpisodeByParams(
                    name = vm.filteredTrigger.value.getValue("name"),
                    episode = vm.filteredTrigger.value.getValue("episode"),
                )
            }
        }

    }

    private fun performSearchEvent(query: String) {
//        vm.getEpisodes(name = query, episode = episode) //fixme
    }

    private fun initRecyclerView() {
        with(binding.rvEpisodes) {
            layoutManager = LinearLayoutManager(requireContext())
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = episodeAdapter
        }
        episodeAdapter.onEpisodeItem = { navigator().openEpisodesDetailFragment(it.id) }
    }

    private fun setUpSwipeToRefresh() {
        binding.srEpisodeList.apply {
            setOnRefreshListener {
                vm.getEpisodeByParams(null, null)
                binding.srEpisodeList.isRefreshing = false
                binding.rvEpisodes.scrollToPosition(0)
            }
        }
    }

    private fun collectUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            vm.episodesFlow.collectLatest  { episodeAdapter.submitData(it) }
        }
    }

    private fun init() {
        arguments?.let {
            episode = it.getString(KEY_EPISODE)
        }
    }
}