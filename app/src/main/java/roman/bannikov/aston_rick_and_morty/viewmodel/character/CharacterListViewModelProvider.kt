package roman.bannikov.aston_rick_and_morty.viewmodel.character

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersUseCase

@ExperimentalPagingApi
class CharacterListViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofitInstance by lazy {
        Retrofit
    }

    private val characterDetailsApi by lazy {
        retrofitInstance.characterDetailsApi
    }

    private val charactersApi by lazy {
        retrofitInstance.characterApi
    }

    private val db by lazy {
        AppDatabase(context = context)
    }

    private val charactersRepository by lazy {
        CharacterRepositoryImpl(
            db = db,
            characterApi = charactersApi,
            characterDetailsApi = characterDetailsApi
        )
    }

    private val getAllCharactersUseCase by lazy {
        GetAllCharactersUseCase(characterRepository = charactersRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterListViewModel(
            getAllCharactersUseCase = getAllCharactersUseCase
        ) as T
    }
}