package roman.bannikov.aston_rick_and_morty.presentation.screens.episodes.episodes_filter_fragment


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_filters_use_cases.GetListEpisodesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class EpisodeFiltersViewModel(
    private val getListEpisodesUseCase: GetListEpisodesUseCase,
) : ViewModel() {
    private val _episodeList = MutableStateFlow<List<String>>(listOf())
    val episodeList: StateFlow<List<String>> = _episodeList


    init {
        viewModelScope.launch {
            getListEpisodesUseCase.execute()
                .collect { list ->
                    _episodeList.value = list
                }
        }
    }

}