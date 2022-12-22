package roman.bannikov.aston_rick_and_morty.view.viewmodels.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersTypesUseCase

class CharacterFilterViewModel(
    private val getListCharactersSpeciesUseCase: GetListCharactersSpeciesUseCase,
    private val getListCharactersTypesUseCase: GetListCharactersTypesUseCase,
): ViewModel() {

    private val _speciesList = MutableStateFlow<List<String>>(listOf())
    val speciesList: StateFlow<List<String>> = _speciesList

    private val _typeList = MutableStateFlow<List<String>>(listOf())
    val typeList: StateFlow<List<String>> = _typeList

    init {
        viewModelScope.launch {
            getListCharactersSpeciesUseCase.execute()
                .collect { list ->
                    _speciesList.value = list
                }
        }

        viewModelScope.launch {
            getListCharactersTypesUseCase.execute()
                .collect { list ->
                    _typeList.value = list
                }
        }
    }
}