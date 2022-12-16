package roman.bannikov.aston_rick_and_morty.view.viewmodels.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersUseCase
import roman.bannikov.aston_rick_and_morty.view.mapper.CharacterDomainToCharacterView
import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView


@ExperimentalPagingApi
class CharacterListViewModel(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
) : ViewModel() {

    private var _charactersFlow = MutableSharedFlow<PagingData<CharacterView>>()
    val charactersFlow = _charactersFlow

    private val _filteredTrigger = MutableStateFlow<MutableMap<String, String?>>(
        mutableMapOf(
            MAP_KEY_CHARACTER_NAME to null,
            MAP_KEY_CHARACTER_GENDER to null,
            MAP_KEY_CHARACTER_STATUS to null,
            MAP_KEY_CHARACTER_SPECIES to null,
            MAP_KEY_CHARACTER_TYPE to null
        )
    )
    val filteredTrigger: MutableStateFlow<MutableMap<String, String?>> = _filteredTrigger

    fun getCharactersByParams(
        name: String?,
        status: String?,
        gender: String?,
        type: String?,
        species: String?
    ) {
        getAllCharactersUseCase.execute(
            name = name,
            status = status,
            gender = gender,
            type = type,
            species = species
        ).onEach {
            _charactersFlow.emit(
                it.map { entity -> CharacterDomainToCharacterView().transform(entity) }
            )
        }.launchIn(viewModelScope)
    }

    companion object{
        const val MAP_KEY_CHARACTER_NAME = "name"
        const val MAP_KEY_CHARACTER_GENDER = "gender"
        const val MAP_KEY_CHARACTER_STATUS = "status"
        const val MAP_KEY_CHARACTER_SPECIES = "species"
        const val MAP_KEY_CHARACTER_TYPE = "type"
    }

}
