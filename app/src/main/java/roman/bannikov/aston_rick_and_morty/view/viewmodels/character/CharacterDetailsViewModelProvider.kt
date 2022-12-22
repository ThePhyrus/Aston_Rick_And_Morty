package roman.bannikov.aston_rick_and_morty.view.viewmodels.character

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.details.GetCharacterByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list.GetAllEpisodesByIdsUseCase


@ExperimentalPagingApi
class CharacterDetailsViewModelProvider(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getAllEpisodesByIdsUseCase: GetAllEpisodesByIdsUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterDetailsViewModel(
            getCharacterByIdUseCase = getCharacterByIdUseCase,
            getAllEpisodesByIdsUseCase = getAllEpisodesByIdsUseCase
        ) as T
    }
}