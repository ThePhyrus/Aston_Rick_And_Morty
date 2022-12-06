package roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases.GetAllEpisodesUseCase

@ExperimentalPagingApi
class EpisodesViewModelProvider(
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodesViewModel(
            getAllEpisodesUseCase = getAllEpisodesUseCase
        ) as T
    }
}