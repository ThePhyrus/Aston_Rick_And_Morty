package roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_filter_fragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersTypesUseCase

class CharacterFiltersViewModelProvider(
    private val getListCharactersTypesUseCase: GetListCharactersTypesUseCase,
    private val getListCharactersSpeciesUseCase: GetListCharactersSpeciesUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterFiltersViewModel(
            getListCharactersSpeciesUseCase = getListCharactersSpeciesUseCase,
            getListCharactersTypesUseCase = getListCharactersTypesUseCase
        ) as T
    }
}