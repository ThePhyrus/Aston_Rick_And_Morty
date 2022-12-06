package roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_filter_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_filters_use_cases.GetListEpisodesUseCase


class EpisodeFilterViewModelProvider(
    private val getListEpisodesUseCase: GetListEpisodesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeFiltersViewModel(
            getListEpisodesUseCase = getListEpisodesUseCase,
        ) as T
    }
}