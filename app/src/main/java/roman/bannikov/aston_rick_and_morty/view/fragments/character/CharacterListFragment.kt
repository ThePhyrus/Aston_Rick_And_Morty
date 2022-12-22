package roman.bannikov.aston_rick_and_morty.view.fragments.character

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
import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharacterListBinding
import roman.bannikov.aston_rick_and_morty.di.App
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.character.CharacterListAdapter
import roman.bannikov.aston_rick_and_morty.view.viewmodels.character.CharacterListViewModel
import roman.bannikov.aston_rick_and_morty.view.viewmodels.character.CharacterListViewModelProvider
import javax.inject.Inject


@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@FlowPreview
class CharacterListFragment : Fragment() {

    @Inject
    lateinit var characterListViewModelProvider: CharacterListViewModelProvider

    private var _binding: FragmentCharacterListBinding? = null
    private val binding: FragmentCharacterListBinding get() = _binding!!

    private lateinit var viewModel: CharacterListViewModel

    private var characterListAdapter: CharacterListAdapter = CharacterListAdapter()

    private var params: MutableMap<String, String?> = mutableMapOf(
        "name" to null,
        "gender" to null,
        "status" to null,
        "species" to null,
        "type" to null
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        binding.btnFilterCharacter.setOnClickListener {
            navigator().launchCharacterFilterFragment()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext().applicationContext as App).appComponent.inject(this)

        viewModel = ViewModelProvider(
            this,
            characterListViewModelProvider
        )[CharacterListViewModel::class.java]

        if (
            params["gender"] != null ||
            params["status"] != null ||
            params["type"] != null ||
            params["species"] != null
        ) viewModel.filteredTrigger.value = params


        initViewModel()
        initRecyclerView()
        collectUiState()
        setUpSwipeToRefresh()


        binding.svCharacter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                performSearchEvent(query = newText)
                return false
            }

        })
        observeViewModel()
    }

    private fun initViewModel() {


    }

    private fun observeViewModel() {
        lifecycle.coroutineScope.launch {
            viewModel.filteredTrigger.collect {
                viewModel.getCharactersByParams(
                    name = viewModel.filteredTrigger.value.getValue
                        (CharacterListViewModel.MAP_KEY_CHARACTER_NAME),
                    gender = viewModel.filteredTrigger.value.getValue
                        (CharacterListViewModel.MAP_KEY_CHARACTER_GENDER),
                    status = viewModel.filteredTrigger.value.getValue
                        (CharacterListViewModel.MAP_KEY_CHARACTER_STATUS),
                    species = viewModel.filteredTrigger.value.getValue
                        (CharacterListViewModel.MAP_KEY_CHARACTER_SPECIES),
                    type = viewModel.filteredTrigger.value.getValue
                        (CharacterListViewModel.MAP_KEY_CHARACTER_TYPE)
                )
            }
        }
    }

    private fun performSearchEvent(query: String) {
//        vm.getEpisodes(name = query, episode = episode)
    }


    private fun initRecyclerView() {
        with(binding.rvCharacters) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = characterListAdapter
        }
        characterListAdapter.onCharacterItem = { navigator().launchCharacterDetailsFragment(it.id) }
    }

    private fun setUpSwipeToRefresh() {
        binding.srCharacterList.apply {
            setOnRefreshListener {
                viewModel.getCharactersByParams(null, null, null, null, null)
                binding.srCharacterList.isRefreshing = false
                binding.rvCharacters.scrollToPosition(0)
            }
        }
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.charactersFlow.collectLatest { characterListAdapter.submitData(it) }
        }
    }

    private fun init() {
        arguments?.let {
            params["gender"] = it.getString(KEY_GENDER)
            params["status"] = it.getString(KEY_STATUS)
            params["species"] = it.getString(KEY_SPECIES)
            params["type"] = it.getString(KEY_TYPE)
        }
    }

    companion object {
        private const val KEY_GENDER: String = "KEY_GENDER"
        private const val KEY_STATUS: String = "KEY_STATUS"
        private const val KEY_SPECIES: String = "KEY_SPECIES"
        private const val KEY_TYPE: String = "KEY_TYPE"

        @JvmStatic
        fun newInstance(
            gender: String?,
            status: String?,
            species: String?,
            type: String?
        ) =
            CharacterListFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_GENDER, gender)
                    putString(KEY_STATUS, status)
                    putString(KEY_SPECIES, species)
                    putString(KEY_TYPE, type)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}