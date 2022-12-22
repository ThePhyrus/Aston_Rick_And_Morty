package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.filter.GetListEpisodesUseCase


class EpisodeFilterViewModelProvider(
    private val getListEpisodesUseCase: GetListEpisodesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeFilterViewModel(
            getListEpisodesUseCase = getListEpisodesUseCase,
        ) as T
    }
}