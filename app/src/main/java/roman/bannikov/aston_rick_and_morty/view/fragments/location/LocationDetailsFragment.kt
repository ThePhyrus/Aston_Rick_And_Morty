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
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentLocationDetailsBinding
import roman.bannikov.aston_rick_and_morty.presentation.models.location.LocationPresentation
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.adapters.character.CharacterListForDetailsAdapter
import roman.bannikov.aston_rick_and_morty.viewmodel.location.LocationDetailsViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.location.LocationDetailsViewModelProvider
import kotlin.properties.Delegates

@ExperimentalPagingApi
class LocationDetailsFragment : Fragment() {

    private lateinit var binding: FragmentLocationDetailsBinding
    private lateinit var vm: LocationDetailsViewModel
    private var characterListForDetailsAdapter: CharacterListForDetailsAdapter? = null

    private var locationId by Delegates.notNull<Int>()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(
            this,
            LocationDetailsViewModelProvider(requireContext())
        )[LocationDetailsViewModel::class.java]
        vm.getLocation(locationId)
        initView()
        observeVm()
    }

    private fun initView() {
        characterListForDetailsAdapter = CharacterListForDetailsAdapter()

        with(binding.rvLocationDetails) {
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
            vm.locationDetails.observe(viewLifecycleOwner, Observer {
                vm.getEpisodesList(it.residentsIds)
                initUI(it)
            })
        }
    }

    private fun initUI(locationDetails: LocationPresentation) {
        binding.tvLocationNameInLocationDetails.text = locationDetails.name
        binding.tvLocationTypeInLocationDetails.text = "Type: ${locationDetails.type}"
        binding.tvLocationDimensionInLocationDetails.text = "Dimension: ${locationDetails.dimension}"
    }

    private fun init() {
        arguments?.let {
            locationId = it.getInt(KEY_LOCATION_ID)
        }
    }
}