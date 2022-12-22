package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories.EpisodeSettingsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.preferences.EpisodeSettingsPref
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.filter.GetListEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.settings.EpisodesSettingsUseCases

class EpisodeFilterViewModelProvider(
    private val getListEpisodesUseCase: GetListEpisodesUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeFilterViewModel(
            getListEpisodesUseCase = getListEpisodesUseCase,
        ) as T
    }
}