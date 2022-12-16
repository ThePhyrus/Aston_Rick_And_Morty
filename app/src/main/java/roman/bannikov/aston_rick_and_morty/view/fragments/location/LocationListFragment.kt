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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentLocationListBinding
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.location.LocationAdapter
import roman.bannikov.aston_rick_and_morty.viewmodel.location.LocationListViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.location.LocationListViewModelProvider

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class LocationListFragment : Fragment() {

    private lateinit var binding: FragmentLocationListBinding
    private var locationAdapter: LocationAdapter = LocationAdapter()

    private var params: MutableMap<String, String?> = mutableMapOf(
        "name" to null,
        "dimension" to null,
        "type" to null
    )

    private lateinit var vm: LocationListViewModel

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(
            this,
            LocationListViewModelProvider(requireContext())
        )[LocationListViewModel::class.java]

        initRecyclerView()
        collectUiState()

        if (
            params["name"] != null ||
            params["type"] != null ||
            params["dimension"] != null
        ) vm.filteredTrigger.value = params

        setUpSwipeToRefresh()

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
        observeVM()
    }

    private fun observeVM() {

        lifecycle.coroutineScope.launch {
            vm.filteredTrigger.observe(viewLifecycleOwner, Observer {
                vm.getLocationsByParams(
                    name = vm.filteredTrigger.value?.getValue("name"),
                    type = vm.filteredTrigger.value?.getValue("type"),
                    dimension = vm.filteredTrigger.value?.getValue("dimension"),
                )
            })
        }
    }

    private fun initRecyclerView() {
        with(binding.rvLocations) {
            layoutManager = LinearLayoutManager(requireContext())
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
                vm.getLocationsByParams(null, null, null)
                binding.srLocationList.isRefreshing = false
                binding.rvLocations.scrollToPosition(0)
            }
        }
    }

    private fun collectUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            vm.locationsFlow.collectLatest { locationAdapter?.submitData(it) }
        }
    }

    private fun init() {
        arguments?.let {
            params["type"] = it.getString(KEY_TYPE)
            params["dimension"] = it.getString(KEY_DIMENSION)
        }
    }
}