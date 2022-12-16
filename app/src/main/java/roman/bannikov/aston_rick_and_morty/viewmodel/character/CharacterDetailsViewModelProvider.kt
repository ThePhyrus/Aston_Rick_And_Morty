package roman.bannikov.aston_rick_and_morty.viewmodel.character

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.details.GetCharacterByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list.GetAllEpisodesByIdsUseCase


@ExperimentalPagingApi
class CharacterDetailsViewModelProvider(
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

    private val episodesApi by lazy {
        retrofit.episodeApi
    }

    private val db by lazy {
        AppDatabase(context = context)
    }

    private val characterDetailsRepository by lazy {
        CharacterDetailsRepositoryImpl(characterDetailsApi = characterDetailsApi, db = db)
    }

    private val episodesRepository by lazy {
        EpisodeRepositoryImpl(
            episodeApi = episodesApi,
            episodeDetailsApi = episodeDetailsApi,
            db = db)
    }

    private val getCharacterByIdUseCase by lazy {
        GetCharacterByIdUseCase(characterDetailsRepository = characterDetailsRepository)
    }

    private val getAllEpisodesByIdsUseCase by lazy {
        GetAllEpisodesByIdsUseCase(episodesRepository = episodesRepository)
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(
            getCharacterByIdUseCase = getCharacterByIdUseCase,
            getAllEpisodesByIdsUseCase = getAllEpisodesByIdsUseCase
        ) as T
    }
}