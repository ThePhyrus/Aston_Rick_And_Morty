package roman.bannikov.aston_rick_and_morty.view.viewmodels.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.episode.details.GetEpisodeByIdUseCase
import roman.bannikov.aston_rick_and_morty.view.mapper.CharacterDomainToCharacterView
import roman.bannikov.aston_rick_and_morty.view.mapper.EpisodeDomainToEpisodeView
import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView
import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView

class EpisodeDetailsViewModel(
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
    private val getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
): ViewModel() {

    private val _episodeDetails = MutableLiveData<EpisodeView>()
    val episodeDetails: MutableLiveData<EpisodeView> = _episodeDetails

    private val _charactersList = MutableLiveData<List<CharacterView>>()
    val charactersList: MutableLiveData<List<CharacterView>> = _charactersList

    fun getEpisode(id: Int) {
        viewModelScope.launch {
            _episodeDetails.value =
                EpisodeDomainToEpisodeView().transform(getEpisodeByIdUseCase.execute(id = id))
        }
    }

    fun getEpisodesList(ids: List<Int>) {
        viewModelScope.launch {
            _charactersList.value =
                getAllCharactersByIdsUseCase.execute(ids = ids).map {
                    CharacterDomainToCharacterView().transform(it)
                }
        }
    }
}