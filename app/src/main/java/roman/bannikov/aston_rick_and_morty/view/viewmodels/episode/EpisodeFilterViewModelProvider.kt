package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.episode.EpisodeFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories.EpisodeSettingsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.data.storage.sharedPref.EpisodeSettingsPref
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.filter.GetListEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.settings.EpisodesSettingsUseCases

class EpisodeFilterViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        AppDatabase(context = context)
    }

    private val getEpisodeFiltersRepository by lazy {
        EpisodeFilterRepositoryImpl(db = db)
    }
    private val episodeSettingsPref by lazy {
        EpisodeSettingsPref(context = context)
    }

    private val episodesSettingsRepository by lazy {
        EpisodeSettingsRepositoryImpl(episodeSettingsPref = episodeSettingsPref)
    }

    private val getListEpisodesUseCase by lazy {
        GetListEpisodesUseCase(getEpisodeFiltersRepository = getEpisodeFiltersRepository)
    }

    private val episodesSettingsUseCase by lazy {
        EpisodesSettingsUseCases(episodesSettingsRepository = episodesSettingsRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EpisodeFilterViewModel(
            getListEpisodesUseCase = getListEpisodesUseCase,
            episodesSettingsUseCase = episodesSettingsUseCase
        ) as T
    }
}