package roman.bannikov.aston_rick_and_morty.view.fragments.character

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharacterFilterBinding
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.viewmodel.character.CharacterFilterViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.character.CharacterFilterViewModelProvider


class CharacterFilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentCharacterFilterBinding? = null
    private val binding: FragmentCharacterFilterBinding get() = _binding!!

    private lateinit var viewModel: CharacterFilterViewModel

    private var species: String = ""
    private var speciesList: MutableList<String> = mutableListOf<String>()
    private var type: String = ""
    private var typesList: MutableList<String> = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterFilterBinding.inflate(layoutInflater, container, false)
        initButtons()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            CharacterFilterViewModelProvider(requireContext())
        )[CharacterFilterViewModel::class.java]
        observeViewModel()
    }

    private fun filterSpecies(params: List<String>) {
        val typedArray = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.characters_species))
            .setSingleChoiceItems(typedArray, 0, null)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                if (typedArray.isNotEmpty()) {
                    species = typedArray[selectedPosition]
                }

            }
            .setNegativeButton(getString(R.string.cancel), null)
            .setCancelable(false)
            .show()
    }

    private fun filterType(params: List<String>) {
        val typedArray = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.character_types))
            .setSingleChoiceItems(typedArray, 0, null)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                if (typedArray.isNotEmpty()) {
                    type = typedArray[selectedPosition]
                }

            }
            .setNegativeButton(getString(R.string.cancel), null)
            .setCancelable(false)
            .show()
    }

    private fun filterGender(): String {
        binding.cgCharacterGenderFilter.children
            .toList()
            .filter { (it as Chip).isChecked }
            .forEach { return (it as Chip).text.toString() }
        return ""
    }

    private fun filterStatus(): String {
        binding.cgCharacterStatusFilter.children
            .toList()
            .filter { (it as Chip).isChecked }
            .forEach { return (it as Chip).text.toString() }
        return ""
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.speciesList.collect {
                    speciesList.addAll(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.typeList.collect {
                    typesList.addAll(it)
                }
            }
        }
    }

    private fun initButtons() {
        binding.btnApplyFilterCharacter.setOnClickListener {
            val gender = filterGender()
            val status = filterStatus()

            navigator().launchFilteredCharacterListFragment(
                gender = gender,
                status = status,
                type = type,
                species = species
            )
            dismiss()
        }

        binding.btnFilterCharacterType.setOnClickListener {
            filterType(typesList)
        }

        binding.btnFilterCharacterSpecies.setOnClickListener {
            filterSpecies(speciesList)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}