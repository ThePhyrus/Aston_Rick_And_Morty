package roman.bannikov.aston_rick_and_morty.viewmodel.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersTypesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CharacterFilterViewModel(
    private val getListCharactersSpeciesUseCase: GetListCharactersSpeciesUseCase,
    private val getListTypesSpeciesUseCase: GetListCharactersTypesUseCase,
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
            getListTypesSpeciesUseCase.execute()
                .collect { list ->
                    _typeList.value = list
                }
        }
    }
}