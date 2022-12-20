package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.details.GetEpisodeByIdUseCase


@ExperimentalPagingApi
class EpisodeDetailsViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofit by lazy {
        Retrofit
    }

    private val characterDetailsApi by lazy {
        retrofit.characterDetailsApi
    }

    private val episodeDetailsApi by lazy {
        retrofit.episodeDetailsApi
    }

    private val charactersApi by lazy {
        retrofit.characterApi
    }

    private val db by lazy {
        AppDatabase(context = context)
    }

    private val episodeDetailsRepository by lazy {
        EpisodeDetailsRepositoryImpl(episodeDetailsApi = episodeDetailsApi, db = db)
    }

    private val charactersRepository by lazy {
        CharacterRepositoryImpl(
            characterDetailsApi = characterDetailsApi,
            characterApi = charactersApi,
            db = db
        )
    }

    private val getEpisodeByIdUseCase by lazy {
        GetEpisodeByIdUseCase(episodeDetailsRepository = episodeDetailsRepository)
    }

    private val getAllCharactersByIdsUseCase by lazy {
        GetAllCharactersByIdsUseCase(characterRepository = charactersRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeDetailsViewModel(
            getEpisodeByIdUseCase = getEpisodeByIdUseCase,
            getAllCharactersByIdsUseCase = getAllCharactersByIdsUseCase
        ) as T
    }
}