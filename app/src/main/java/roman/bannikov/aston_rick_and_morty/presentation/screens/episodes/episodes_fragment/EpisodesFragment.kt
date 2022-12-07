package roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import roman.bannikov.aston_rick_and_morty.presentation.adapters.episodes_adapter.EpisodesAdapter
import roman.bannikov.aston_rick_and_morty.presentation.navigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodesBinding


@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class EpisodesFragment : Fragment() {

    private lateinit var binding: FragmentEpisodesBinding
    private var episodesAdapter: EpisodesAdapter = EpisodesAdapter()

    private var episode: String? = null

    private lateinit var vm: EpisodesViewModel

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

        vm = ViewModelProvider(
            this,
            EpisodesViewModelProvider(requireContext())
        )[EpisodesViewModel::class.java]

        initRecyclerView()
        collectUiState()

        if (episode != null) vm.filteredTrigger.value["episode"] = episode

        setUpSwipeToRefresh()

        binding.btnFilterEpisodes.setOnClickListener {
            navigator().openEpisodesFilterFragment()
        }

        binding.searchEpisodes.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
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
//        vm.getEpisodes(name = query, episode = episode)
    }

    private fun initRecyclerView() {
        with(binding.rvEpisodes) {
            layoutManager = LinearLayoutManager(requireContext())
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = episodesAdapter
        }
        episodesAdapter.onEpisodeItem = { navigator().openEpisodesDetailFragment(it.id) }
    }

    private fun setUpSwipeToRefresh() {
        binding.swipeRefreshEpisodes.apply {
            setOnRefreshListener {
                vm.getEpisodeByParams(null, null)
                binding.swipeRefreshEpisodes.isRefreshing = false
                binding.rvEpisodes.scrollToPosition(0)
            }
        }
    }

    private fun collectUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            vm.episodesFlow.collectLatest  { episodesAdapter.submitData(it) }
        }
    }

    private fun init() {
        arguments?.let {
            episode = it.getString(KEY_EPISODE)
        }
    }
}