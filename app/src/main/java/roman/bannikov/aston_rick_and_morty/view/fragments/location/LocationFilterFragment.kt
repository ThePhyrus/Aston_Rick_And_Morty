package roman.bannikov.aston_rick_and_morty.view.fragments.location

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.FragmentLocationFilterBinding
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationFilterViewModel
import roman.bannikov.aston_rick_and_morty.view.viewmodels.location.LocationFilterViewModelProvider


class LocationFilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentLocationFilterBinding? = null
    private val binding: FragmentLocationFilterBinding get() = _binding!!

    private lateinit var viewModel: LocationFilterViewModel

    private var type: String? = null
    private var typesList: MutableList<String> = mutableListOf<String>()
    private var dimension: String? = null
    private var dimensionsList: MutableList<String> = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationFilterBinding.inflate(layoutInflater, container, false)
        initButtons()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        observeViewModel()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            LocationFilterViewModelProvider(requireContext())
        )[LocationFilterViewModel::class.java]
    }

    private fun initButtons() {
        with(binding) {
            btnApplyFilterLocation.setOnClickListener {
                navigator().launchFilteredLocationListFragment(type = type, dimension = dimension)
                dismiss()
            }
            btnFilterLocationDimension.setOnClickListener {
                filterDimension(dimensionsList)
            }
            btnFilterLocationType.setOnClickListener {
                filterType(typesList)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.dimensionList.collect {
                    dimensionsList.addAll(it)
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

    private fun filterType(params: List<String>) {
        val typedArray = params.toTypedArray()
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.alert_dialog_title_location_types))
            .setSingleChoiceItems(typedArray, 0, null)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                if (typedArray.isNotEmpty()) {
                    type = typedArray[selectedPosition]
                }
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    private fun filterDimension(params: List<String>) {
        val typedArray = params.toTypedArray()
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.alert_dialog_title_dimension_types))
            .setSingleChoiceItems(typedArray, 0, null)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                if (typedArray.isNotEmpty()) {
                    dimension = typedArray[selectedPosition]
                }

            }
            .setNegativeButton(getString(R.string.cancel), null)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}