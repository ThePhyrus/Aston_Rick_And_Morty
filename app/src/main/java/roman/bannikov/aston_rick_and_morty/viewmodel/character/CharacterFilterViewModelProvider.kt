package roman.bannikov.aston_rick_and_morty.viewmodel.character

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersSpeciesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.filter.GetListCharactersTypesUseCase

class CharacterFilterViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        AppDatabase(context = context)
    }

    private val getCharacterFiltersRepository by lazy {
        CharacterFilterRepositoryImpl(db = db)
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