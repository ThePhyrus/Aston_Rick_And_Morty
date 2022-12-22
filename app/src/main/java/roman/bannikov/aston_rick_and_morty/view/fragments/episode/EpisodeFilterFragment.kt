package roman.bannikov.aston_rick_and_morty.view.fragments.episode

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.R
import roman.bannikov.aston_rick_and_morty.databinding.FragmentEpisodeFilterBinding
import roman.bannikov.aston_rick_and_morty.di.App
import roman.bannikov.aston_rick_and_morty.utils.navigator
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeFilterViewModel
import roman.bannikov.aston_rick_and_morty.view.viewmodels.episode.EpisodeFilterViewModelProvider
import javax.inject.Inject

class EpisodeFilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEpisodeFilterBinding? = null
    private val binding: FragmentEpisodeFilterBinding get() = _binding!!

    @Inject
    lateinit var episodeFilterViewModelProvider: EpisodeFilterViewModelProvider
    private lateinit var viewModel: EpisodeFilterViewModel

    private var episode: String? = null
    private var episodeList: MutableList<String> = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEpisodeFilterBinding.inflate(layoutInflater, container, false)
        initButtons()
        return binding.root
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireContext().applicationContext as App).appComponent.inject(this)
        initViewModel()
        observeViewModel()
    }

    private fun initButtons() {
        binding.btnApplyFilterEpisode.setOnClickListener {
            navigator().launchFilteredEpisodeListFragment(episode = episode)
            dismiss()
        }

        binding.btnChooseEpisode.setOnClickListener {
            filterEpisode(episodeList)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            episodeFilterViewModelProvider
        )[EpisodeFilterViewModel::class.java]
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.episodeList.collect {
                    episodeList.addAll(it)
                }
            }
        }
    }

    private fun filterEpisode(params: List<String>) {
        val typedArray = params.toTypedArray()

        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.alert_dialog_title_episodes))
            .setSingleChoiceItems(typedArray, 0, null)
            .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
                val selectedPosition = (dialog as AlertDialog).listView.checkedItemPosition
                if (typedArray.isNotEmpty()) {
                    episode = typedArray[selectedPosition]
                }
                dialog.dismiss()
            }
            .setNegativeButton((getString(R.string.cancel)), null)
            .setCancelable(false)
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}