package roman.bannikov.aston_rick_and_morty.presentation.screens.characters.character_details_fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.RetrofitInstance
import roman.bannikov.aston_rick_and_morty.data.repositories.characters_repositories.CharacterDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.episodes_repositories.EpisodesRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_details_use_cases.GetCharacterByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases.GetAllEpisodesByIdsUseCase


@ExperimentalPagingApi
class CharacterDetailsViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofit by lazy {
        RetrofitInstance
    }

    private val characterDetailsApi by lazy {
        retrofit.characterDetailsApi
    }

    private val episodeDetailsApi by lazy {
        retrofit.episodeDetailsApi
    }

    private val episodesApi by lazy {
        retrofit.episodesApi
    }

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val characterDetailsRepository by lazy {
        CharacterDetailsRepositoryImpl(characterDetailsApi = characterDetailsApi, db = db)
    }

    private val episodesRepository by lazy {
        EpisodesRepositoryImpl(
            episodesApi = episodesApi,
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