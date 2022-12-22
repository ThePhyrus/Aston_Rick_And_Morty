package roman.bannikov.aston_rick_and_morty.view.viewmodels.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersUseCase

@ExperimentalPagingApi
class CharacterListViewModelProvider(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterListViewModel(
            getAllCharactersUseCase = getAllCharactersUseCase
        ) as T
    }
}
