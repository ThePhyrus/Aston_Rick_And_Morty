package roman.bannikov.aston_rick_and_morty.viewmodel.character

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.characters_repositories.GetCharacterFiltersRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_filters_use_case.GetListCharactersTypesUseCase

class CharacterFilterViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val getCharacterFiltersRepository by lazy {
        GetCharacterFiltersRepositoryImpl(db = db)
    }

    private val getListTypesSpeciesUseCase by lazy {
        GetListCharactersTypesUseCase(getCharacterFiltersRepository = getCharacterFiltersRepository)
    }

    private val getListCharactersSpeciesUseCase by lazy {
        GetListCharactersSpeciesUseCase(getCharacterFiltersRepository = getCharacterFiltersRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterFilterViewModel(
            getListCharactersSpeciesUseCase = getListCharactersSpeciesUseCase,
            getListTypesSpeciesUseCase = getListTypesSpeciesUseCase
        ) as T
    }
}