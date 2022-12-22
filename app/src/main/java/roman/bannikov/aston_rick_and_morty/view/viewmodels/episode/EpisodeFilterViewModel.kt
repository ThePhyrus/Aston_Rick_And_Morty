package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.filter.GetListEpisodesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.settings.EpisodesSettingsUseCases

class EpisodeFilterViewModel(
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