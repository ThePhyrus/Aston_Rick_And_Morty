package roman.bannikov.aston_rick_and_morty.view.fragments.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentLocationListBinding
import roman.bannikov.aston_rick_and_morty.di.App
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.location.LocationAdapter
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationListViewModel
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationListViewModelProvider
import javax.inject.Inject

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class LocationListFragment : Fragment() {

    private var _binding:FragmentLocationListBinding? = null
    private val binding: FragmentLocationListBinding get() = _binding!!

    @Inject
    lateinit var locationListViewModelProvider: LocationListViewModelProvider
    private lateinit var viewModel: LocationListViewModel

    private var locationAdapter: LocationAdapter = LocationAdapter()

    private var params: MutableMap<String, String?> = mutableMapOf(
        "name" to null,
        "dimension" to null,
        "type" to null
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            params["type"] = it.getString(KEY_TYPE)
            params["dimension"] = it.getString(KEY_DIMENSION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationListBinding.inflate(layoutInflater, container, false)
        binding.btnFilterLocation.setOnClickListener {
            navigator().launchLocationFilterFragment()
        }

        binding.svLocation.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                performSearchEvent(query = newText)
                return false
            }

        })
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
            locationListViewModelProvider
        )[LocationListViewModel::class.java]
        if (
            params["name"] != null ||
            params["type"] != null ||
            params["dimension"] != null
        ) viewModel.filteredTrigger.value = params
    }

    private fun observeViewModel() {

        lifecycle.coroutineScope.launch {
            viewModel.filteredTrigger.observe(viewLifecycleOwner, Observer {
                viewModel.getLocationsByParams(
                    name = viewModel.filteredTrigger.value?.getValue("name"),
                    type = viewModel.filteredTrigger.value?.getValue("type"),
                    dimension = viewModel.filteredTrigger.value?.getValue("dimension"),
                )
            })
        }
    }

    private fun initRecyclerView() {
        with(binding.rvLocations) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = locationAdapter
        }
        locationAdapter.onLocationItem = { navigator().launchLocationDetailsFragment(it.id) }
    }

    private fun performSearchEvent(query: String) {
//        vm.getEpisodes(name = query, episode = episode)
    }

    private fun setUpSwipeToRefresh() {
        binding.srLocationList.apply {
            setOnRefreshListener {
                viewModel.getLocationsByParams(null, null, null)
                binding.srLocationList.isRefreshing = false
                binding.rvLocations.scrollToPosition(0)
            }
        }
    }

    private fun collectUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.locationsFlow.collectLatest { locationAdapter?.submitData(it) }
        }
    }


    companion object {
        private const val KEY_TYPE: String = "KEY_TYPE"
        private const val KEY_DIMENSION: String = "KEY_DIMENSION"

        @JvmStatic
        fun newInstance(
            types: String?,
            dimensions: String?
        ) =
            LocationListFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TYPE, types)
                    putString(KEY_DIMENSION, dimensions)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}