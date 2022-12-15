package roman.bannikov.aston_rick_and_morty.viewmodel.character

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

    private val _filteredTrigger = MutableStateFlow<MutableMap<String, String?>>(
        mutableMapOf(
            "name" to null,
            "gender" to null,
            "status" to null,
            "species" to null,
            "type" to null
        )
    )
    val filteredTrigger: MutableStateFlow<MutableMap<String, String?>> = _filteredTrigger

    private var _charactersFlow = MutableSharedFlow<PagingData<CharacterView>>()
    val charactersFlow = _charactersFlow

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
                it.map { obj -> CharacterDomainToCharacterView().transform(obj) }
            )
        }.launchIn(viewModelScope)
    }
}
