package roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_fragment

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map

import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases.GetAllEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetEpisodePresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation
import kotlinx.coroutines.flow.*


@ExperimentalPagingApi
class EpisodesViewModel(
    private val getAllEpisodesUseCase: GetAllEpisodesUseCase
) : ViewModel() {


    private val _filteredTrigger = MutableStateFlow<MutableMap<String, String?>>(
        mutableMapOf(
            "name" to null,
            "episode" to null
        )
    )

    val filteredTrigger: MutableStateFlow<MutableMap<String, String?>> = _filteredTrigger

    private var _episodesFlow = MutableSharedFlow<PagingData<EpisodePresentation>>()
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
                it.map { obj -> GetEpisodePresentationModel().transform(obj) }
            )
        }.launchIn(viewModelScope)

    }
}