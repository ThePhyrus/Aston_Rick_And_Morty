package roman.bannikov.aston_rick_and_morty.view.viewmodels.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersTypesUseCase

class CharacterFilterViewModelProvider(
    private val getListCharactersTypesUseCase: GetListCharactersTypesUseCase,
    private val getListCharactersSpeciesUseCase: GetListCharactersSpeciesUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterFilterViewModel(
            getListCharactersSpeciesUseCase = getListCharactersSpeciesUseCase,
            getListCharactersTypesUseCase = getListCharactersTypesUseCase
        ) as T
    }
}