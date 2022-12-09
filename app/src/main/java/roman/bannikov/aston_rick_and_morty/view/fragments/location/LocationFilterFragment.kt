package roman.bannikov.aston_rick_and_morty.view.fragments.location

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import roman.bannikov.aston_rick_and_morty.presentation.navigator
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentLocationFilterBinding
import roman.bannikov.aston_rick_and_morty.viewmodel.location.LocationFilterViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.location.LocationFilterViewModelProvider


class LocationFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentLocationFilterBinding

    private var type: String? = null
    private var dimension: String? = null
    private var dimensionsList: MutableList<String> = mutableListOf<String>()
    private var typesList: MutableList<String> = mutableListOf<String>()
    private lateinit var vm: LocationFilterViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationFilterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(
            this,
            LocationFilterViewModelProvider(requireContext())
        )[LocationFilterViewModel::class.java]
        observeVm()

        binding.btnApplyFilterLocation.setOnClickListener {
            navigator().openLocationsFragmentWithArg(type = type, dimension = dimension)
            dismiss()
        }

        binding.btnFilterLocationDimension.setOnClickListener {
            getDimension(dimensionsList)
        }

        binding.btnFilterLocationType.setOnClickListener {
            getType(typesList)
        }
    }

    private fun observeVm() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                vm.dimensionList.collect {
                    dimensionsList.addAll(it)
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

    private fun getType(params: List<String>) {
        val typesArr = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Location types")
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

    private fun getDimension(params: List<String>) {
        val typesArr = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Dimensions types")
            .setSingleChoiceItems(typesArr, 0, null)
            .setPositiveButton("Confirm") { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                Log.e("checkedItem", "$selectedPosition");
                if(typesArr.isNotEmpty()){ dimension = typesArr[selectedPosition] }

            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}