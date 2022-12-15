package roman.bannikov.aston_rick_and_morty.viewmodel.episode

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
            "name" to null,
            "episode" to null
        )
    )

    val filteredTrigger: MutableStateFlow<MutableMap<String, String?>> = _filteredTrigger

    private var _episodesFlow = MutableSharedFlow<PagingData<EpisodeView>>()
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
}