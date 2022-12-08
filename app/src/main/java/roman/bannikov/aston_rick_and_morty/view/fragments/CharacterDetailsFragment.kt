package roman.bannikov.aston_rick_and_morty.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

import roman.bannikov.aston_rick_and_morty.presentation.adapters.character_details_adapter.EpisodeListForDetailsAdapter
import roman.bannikov.aston_rick_and_morty.presentation.models.character.CharacterPresentation
import roman.bannikov.aston_rick_and_morty.presentation.navigator
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharacterDetailsBinding
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.character_details_fragment.CharacterDetailsViewModel
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.character_details_fragment.CharacterDetailsViewModelProvider
import kotlin.properties.Delegates


@ExperimentalPagingApi
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private lateinit var vm: CharacterDetailsViewModel
    private var episodeListForDetailsAdapter: EpisodeListForDetailsAdapter? = null


    private var characterId by Delegates.notNull<Int>()
    private var lastLocationId: Int? = null
    private var originLocationId: Int? = null

    companion object {
        private const val KEY_CHARACTER_ID: String = "KEY_CHARACTER_ID"

        @JvmStatic
        fun newInstance(
            characterId: Int
        ) =
            CharacterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_CHARACTER_ID, characterId)
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
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(
            this,
            CharacterDetailsViewModelProvider(requireContext())
        )[CharacterDetailsViewModel::class.java]
        vm.getCharacter(characterId)
        initView()
        observeVm()

        binding.tvCharacterOrigin.setOnClickListener {
            if (originLocationId != null) {
                navigator().openLocationsDetailFragment(locationId = originLocationId!!)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Character's place of origin unknown.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.tvCharacterLocation.setOnClickListener {
            if (lastLocationId != null) {
                navigator().openLocationsDetailFragment(locationId = lastLocationId!!)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Character's last location is unknown.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun initView() {
        episodeListForDetailsAdapter = EpisodeListForDetailsAdapter()

        with(binding.rvOnCharacterDetailsFragment) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = episodeListForDetailsAdapter
        }
        episodeListForDetailsAdapter!!.onEpisodeItem = {navigator().openEpisodesDetailFragment(it.id)}
    }

    private fun observeVm() {
        lifecycle.coroutineScope.launch {
            vm.episodesList.observe(viewLifecycleOwner, Observer {
                episodeListForDetailsAdapter!!.submitList(it)
            })
        }

        lifecycle.coroutineScope.launch {
            vm.characterDetails.observe(viewLifecycleOwner, Observer {
                vm.getEpisodesList(it.episodeIds)
                initUI(it)
            })
        }
    }

    private fun initUI(characterDetails: CharacterPresentation) {
        if (characterDetails.lastLocation.getValue("location_id").isNotEmpty()) {
            lastLocationId = characterDetails.lastLocation.getValue("location_id").toInt()
        }
        if (characterDetails.originLocation.getValue("location_id").isNotEmpty()) {
            originLocationId = characterDetails.originLocation.getValue("location_id").toInt()
        }
        Glide.with(requireContext())
            .load(characterDetails.imageUrl)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_dissconect)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.ivCharacterImage)

        //fixme переделать xml (убрать харкодные строки)
        binding.tvCharacterName.text = "Name: ${characterDetails.name}"
        binding.tvCharacterStatus.text = "Status: ${characterDetails.status}"
        binding.tvCharacterType.text = "Type: ${characterDetails.type}"
        binding.tvCharacterSpecie.text = "Species: ${characterDetails.species}"
        binding.tvCharacterGender.text = "Gender: ${characterDetails.gender}"
        binding.tvCharacterLocation.text =
            "Location: ${characterDetails.lastLocation.getValue("location_name")}"
        binding.tvCharacterOrigin.text =
            "Origin: ${characterDetails.originLocation.getValue("location_name")}"
    }

    private fun init() {
        arguments?.let {
            characterId = it.getInt(KEY_CHARACTER_ID)
        }
    }
}