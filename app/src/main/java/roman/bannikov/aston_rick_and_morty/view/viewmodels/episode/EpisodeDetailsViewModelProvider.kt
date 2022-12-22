package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.details.GetEpisodeByIdUseCase

@ExperimentalPagingApi
class EpisodeDetailsViewModelProvider(
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
    private val getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailsViewModel(
            getEpisodeByIdUseCase = getEpisodeByIdUseCase,
            getAllCharactersByIdsUseCase = getAllCharactersByIdsUseCase
        ) as T
    }
}