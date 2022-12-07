package roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_filter_fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.episodes_repositories.GetEpisodeFiltersRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories.EpisodeSettingsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.data.storage.sharedPref.EpisodeSettingsPref
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_filters_use_cases.GetListEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.settings.EpisodesSettingsUseCases

class EpisodeFilterViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val getEpisodeFiltersRepository by lazy {
        GetEpisodeFiltersRepositoryImpl(db = db)
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
        return EpisodeFiltersViewModel(
            getListEpisodesUseCase = getListEpisodesUseCase,
            episodesSettingsUseCase = episodesSettingsUseCase
        ) as T
    }
}