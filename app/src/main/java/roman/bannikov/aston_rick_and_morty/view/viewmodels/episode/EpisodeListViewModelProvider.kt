package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list.GetAllEpisodesUseCase


@ExperimentalPagingApi
class EpisodeListViewModelProvider(
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeListViewModel(
            getAllEpisodesUseCase = getAllEpisodesUseCase
        ) as T
    }
}