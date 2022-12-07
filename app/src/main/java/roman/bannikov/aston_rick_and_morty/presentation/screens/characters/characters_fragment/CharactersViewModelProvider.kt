package roman.bannikov.aston_rick_and_morty.presentation.screens.characters.characters_fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.RetrofitInstance
import roman.bannikov.aston_rick_and_morty.data.repositories.characters_repositories.CharactersRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases.GetAllCharactersUseCase

@ExperimentalPagingApi
class CharactersViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofitInstance by lazy {
        RetrofitInstance
    }

    private val characterDetailsApi by lazy {
        retrofitInstance.characterDetailsApi
    }

    private val charactersApi by lazy {
        retrofitInstance.charactersApi
    }

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val charactersRepository by lazy {
        CharactersRepositoryImpl(
            db = db,
            charactersApi = charactersApi,
            characterDetailsApi = characterDetailsApi
        )
    }

    private val getAllCharactersUseCase by lazy {
        GetAllCharactersUseCase(charactersRepository = charactersRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharactersViewModel(
            getAllCharactersUseCase = getAllCharactersUseCase
        ) as T
    }
}