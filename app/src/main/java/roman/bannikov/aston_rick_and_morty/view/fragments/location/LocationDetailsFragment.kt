package roman.bannikov.aston_rick_and_morty.view.fragments.location

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
import roman.bannikov.aston_rick_and_morty.databinding.FragmentLocationDetailsBinding
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.character.CharacterListForDetailsAdapter
import roman.bannikov.aston_rick_and_morty.view.models.location.LocationView
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationDetailsViewModel
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationDetailsViewModelProvider
import kotlin.properties.Delegates

@ExperimentalPagingApi
class LocationDetailsFragment : Fragment() {

    private var _binding: FragmentLocationDetailsBinding? = null
    private val binding: FragmentLocationDetailsBinding get() = _binding!!

    private lateinit var viewModel: LocationDetailsViewModel

    private var characterListForDetailsAdapter: CharacterListForDetailsAdapter? = null

    private var locationId by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationDetailsBinding.inflate(layoutInflater, container, false)
        binding.btnBack.setOnClickListener {
            navigator().goBack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initView()
        observeViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            LocationDetailsViewModelProvider(requireContext())
        )[LocationDetailsViewModel::class.java]
        viewModel.getLocation(locationId)
    }

    private fun initView() {
        characterListForDetailsAdapter = CharacterListForDetailsAdapter()
        with(binding.rvLocationDetails) {
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
            viewModel.locationDetails.observe(viewLifecycleOwner, Observer {
                viewModel.getEpisodesList(it.residentsIds)
                initUI(it)
            })
        }
    }

    private fun initUI(locationDetails: LocationView) {
        binding.tvLocationNameInLocationDetails.text = locationDetails.name
        binding.tvLocationTypeInLocationDetails.text = locationDetails.type
        binding.tvLocationDimensionInLocationDetails.text = locationDetails.dimension
    }

    private fun init() {
        arguments?.let {
            locationId = it.getInt(KEY_LOCATION_ID)
        }
    }

    companion object {
        private const val KEY_LOCATION_ID: String = "KEY_LOCATION_ID"

        @JvmStatic
        fun newInstance(
            locationId: Int,
        ) =
            LocationDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_LOCATION_ID, locationId)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}