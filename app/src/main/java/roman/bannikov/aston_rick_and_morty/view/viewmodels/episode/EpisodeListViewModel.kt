package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.list.GetAllEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.view.mapper.EpisodeDomainToEpisodeView
import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView


@ExperimentalPagingApi
class EpisodeListViewModel(
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
) : ViewModel() {

    private val _filteredTrigger = MutableStateFlow<MutableMap<String, String?>>(
        mutableMapOf(
            MAP_KEY_EPISODE_NAME to null,
            MAP_KEY_EPISODE_CODE to null
        )
    )
    private var _episodesFlow = MutableSharedFlow<PagingData<EpisodeView>>()
    val filteredTrigger: MutableStateFlow<MutableMap<String, String?>> = _filteredTrigger
    val episodesFlow = _episodesFlow

    fun getEpisodeByParams(
        name: String?,
        episode: String?
    ) {
        getAllEpisodesUseCase.execute(
            name = name,
            episode = episode
        ).onEach {
            _episodesFlow.emit(
                it.map { obj -> EpisodeDomainToEpisodeView().transform(obj) }
            )
        }.launchIn(viewModelScope)

    }

    companion object {
        const val MAP_KEY_EPISODE_NAME = "name"
        const val MAP_KEY_EPISODE_CODE = "episode"
    }
}