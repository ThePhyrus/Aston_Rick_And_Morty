package roman.bannikov.aston_rick_and_morty.view.fragments.character

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import roman.bannikov.aston_rick_and_morty.presentation.navigator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentCharacterFilterBinding
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_filter_fragment.CharacterFilterViewModel
import roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_filter_fragment.CharacterFilterViewModelProvider


class CharacterFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCharacterFilterBinding
    private var species: String = ""
    private var type: String = ""
    private var speciesList: MutableList<String> = mutableListOf<String>()
    private var typesList: MutableList<String> = mutableListOf<String>()
    private lateinit var vm: CharacterFilterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterFilterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(
            this,
            CharacterFilterViewModelProvider(requireContext())
        )[CharacterFilterViewModel::class.java]
        observeVm()

        binding.btnApplyFilterCharacter.setOnClickListener {
            val gender = getGender()
            val status = getStatus()

            navigator().openCharactersFragmentWithArg(
                gender = gender,
                status = status,
                type = type,
                species = species
            )
            dismiss()
        }

        binding.btnFilterCharacterType.setOnClickListener {
            getType(typesList)
        }

        binding.btnFilterCharacterSpecies.setOnClickListener {
            getSpecies(speciesList)
        }

    }

    private fun observeVm() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                vm.speciesList.collect {
                    speciesList.addAll(it)
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                vm.typeList.collect {
                    typesList.addAll(it)
                }
            }
        }
    }

    private fun getStatus(): String {
        binding.cgCharacterStatusFilter.children
            .toList()
            .filter { (it as Chip).isChecked }
            .forEach { return (it as Chip).text.toString() }
        return ""
    }

    private fun getGender(): String {
        binding.cgCharacterGenderFilter.children
            .toList()
            .filter { (it as Chip).isChecked }
            .forEach { return (it as Chip).text.toString() }
        return ""
    }

    private fun getSpecies(params: List<String>) {
        val typesArr = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Characters species")
            .setSingleChoiceItems(typesArr, 0, null)
            .setPositiveButton("Confirm") { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                Log.e("checkedItem", "$selectedPosition");
                if(typesArr.isNotEmpty()){ species = typesArr[selectedPosition] }

            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun getType(params: List<String>) {
        val typesArr = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Characters types")
            .setSingleChoiceItems(typesArr, 0, null)
            .setPositiveButton("Confirm") { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                Log.e("checkedItem", "$selectedPosition");
                if(typesArr.isNotEmpty()){ type = typesArr[selectedPosition] }

            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}