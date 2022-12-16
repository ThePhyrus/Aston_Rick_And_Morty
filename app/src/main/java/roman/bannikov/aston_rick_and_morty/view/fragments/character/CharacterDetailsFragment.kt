package roman.bannikov.aston_rick_and_morty.view.fragments.character

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharacterDetailsBinding
import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView
import roman.bannikov.aston_rick_and_morty.utils.navigator

import roman.bannikov.aston_rick_and_morty.view.adapters.character.CharacterDetailsAdapter
import roman.bannikov.aston_rick_and_morty.viewmodel.character.CharacterDetailsViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.character.CharacterDetailsViewModelProvider
import kotlin.properties.Delegates


@ExperimentalPagingApi
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding: FragmentCharacterDetailsBinding get() = _binding!!
    private lateinit var viewModel: CharacterDetailsViewModel
    private var characterDetailsAdapter: CharacterDetailsAdapter? = null


    private var characterId by Delegates.notNull<Int>()
    private var lastLocationId: Int? = null
    private var originLocationId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(KEY_CHARACTER_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(layoutInflater, container, false)

        initButtons()

        return binding.root
    }

    private fun initButtons() {
        with(binding) {
            btnBack.setOnClickListener {
                navigator().goBack()
            }
            tvCharacterOrigin.setOnClickListener {
                if (originLocationId != null) {
                    navigator().launchLocationDetailsFragment(locationId = originLocationId!!)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            tvCharacterLocation.setOnClickListener {
                if (lastLocationId != null) {
                    navigator().launchLocationDetailsFragment(locationId = lastLocationId!!)
                } else {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.error),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            this,
            CharacterDetailsViewModelProvider(requireContext())
        )[CharacterDetailsViewModel::class.java]
        viewModel.getCharacter(characterId)
        initView()
        observeViewModel()


    }

    private fun initView() {
        characterDetailsAdapter = CharacterDetailsAdapter()

        with(binding.rvCharacterDetails) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = characterDetailsAdapter
        }
        characterDetailsAdapter!!.onEpisodeItem =
            { navigator().launchEpisodeDetailsFragment(it.id) }
    }

    private fun observeViewModel() {
        lifecycle.coroutineScope.launch {
            viewModel.episodesList.observe(viewLifecycleOwner, Observer {
                characterDetailsAdapter!!.submitList(it)
            })
        }

        lifecycle.coroutineScope.launch {
            viewModel.characterDetails.observe(viewLifecycleOwner, Observer {
                viewModel.getEpisodesList(it.episodeIds)
                initUI(it)
            })
        }
    }

    private fun initUI(characterDetails: CharacterView) {
        if (characterDetails.lastLocation.getValue(KEY_LOCATION_ID).isNotEmpty()) {
            lastLocationId = characterDetails.lastLocation.getValue(KEY_LOCATION_ID).toInt()
        }
        if (characterDetails.originLocation.getValue(KEY_LOCATION_ID).isNotEmpty()) {
            originLocationId = characterDetails.originLocation.getValue(KEY_LOCATION_ID).toInt()
        }
        Glide.with(requireContext())
            .load(characterDetails.imageUrl)
            .placeholder(R.drawable.ic_loading_placeholder)
            .error(R.drawable.ic_loading_error)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(binding.ivCharacterImage)

        binding.tvCharacterName.text = characterDetails.name
        binding.tvCharacterStatus.text = characterDetails.status
        binding.tvCharacterType.text = characterDetails.type
        binding.tvCharacterSpecie.text = characterDetails.species
        binding.tvCharacterGender.text = characterDetails.gender
        binding.tvCharacterLocation.text = characterDetails.lastLocation.getValue(KEY_LOCATION_NAME)
        binding.tvCharacterOrigin.text = characterDetails.originLocation.getValue(KEY_LOCATION_NAME)

    }

    companion object {
        private const val KEY_CHARACTER_ID: String = "KEY_CHARACTER_ID"
        private const val KEY_LOCATION_ID: String = "location_id"
        private const val KEY_LOCATION_NAME: String = "location_name"

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}