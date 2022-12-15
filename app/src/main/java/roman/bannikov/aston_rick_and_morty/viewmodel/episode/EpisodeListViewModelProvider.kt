package roman.bannikov.aston_rick_and_morty.viewmodel.episode

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list.GetAllEpisodesUseCase

@ExperimentalPagingApi
class EpisodeListViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofitInstance by lazy {
        Retrofit
    }

    private val episodeDetailsApi by lazy {
        retrofitInstance.episodeDetailsApi
    }

    private val episodesApi by lazy {
        retrofitInstance.episodeApi
    }

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val episodesRepository by lazy {
        EpisodeRepositoryImpl(
            db = db,
            episodeDetailsApi = episodeDetailsApi,
            episodeApi = episodesApi
        )
    }

    private val getAllEpisodesUseCase by lazy {
        GetAllEpisodesUseCase(episodesRepository = episodesRepository)
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeListViewModel(
            getAllEpisodesUseCase = getAllEpisodesUseCase
        ) as T
    }
}