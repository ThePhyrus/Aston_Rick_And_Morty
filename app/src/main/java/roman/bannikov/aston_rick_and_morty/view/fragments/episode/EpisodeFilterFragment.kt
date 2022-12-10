package roman.bannikov.aston_rick_and_morty.view.fragments.episode

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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodeFilterBinding
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.viewmodel.episode.EpisodeFilterViewModel
import roman.bannikov.aston_rick_and_morty.viewmodel.episode.EpisodeFilterViewModelProvider

class EpisodeFilterFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentEpisodeFilterBinding
    private var episode: String? = null
    private var episodesList: MutableList<String> = mutableListOf<String>()
    private lateinit var vm: EpisodeFilterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeFilterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProvider(
            this,
            EpisodeFilterViewModelProvider(requireContext())
        )[EpisodeFilterViewModel::class.java]
        observeVm()

        binding.btnApplyFilterEpisode.setOnClickListener {
            navigator().openEpisodesFragmentWithArg(episode = episode)
            dismiss()
        }

        binding.btnChooseEpisode.setOnClickListener {
            getEpisode(episodesList)
        }
    }

    private fun observeVm() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                vm.episodeList.collect {
                    episodesList.addAll(it)
                }
            }
        }
    }

    private fun getEpisode(params: List<String>) {
        val typesArr = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle("Episodes")
            .setSingleChoiceItems(typesArr, 0, null)
            .setPositiveButton("Confirm") { dialog, _ ->
                dialog.dismiss()
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                Log.e("checkedItem", "$selectedPosition");
                if(typesArr.isNotEmpty()){ episode = typesArr[selectedPosition] }

            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}