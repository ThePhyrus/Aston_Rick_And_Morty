package roman.bannikov.aston_rick_and_morty.viewmodel.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_details_use_cases.GetCharacterByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases.GetAllEpisodesByIdsUseCase
import roman.bannikov.aston_rick_and_morty.view.mapper.CharacterDomainToCharacterView
import roman.bannikov.aston_rick_and_morty.view.mapper.EpisodeDomainToEpisodeView
import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView
import roman.bannikov.aston_rick_and_morty.view.models.episode.EpisodeView

class CharacterDetailsViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getAllEpisodesByIdsUseCase: GetAllEpisodesByIdsUseCase
) : ViewModel() {

    private val _characterDetails = MutableLiveData<CharacterView>()
    val characterDetails: MutableLiveData<CharacterView> = _characterDetails

    private val _episodesList = MutableLiveData<List<EpisodeView>>()
    val episodesList: MutableLiveData<List<EpisodeView>> = _episodesList

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _characterDetails.value =
                CharacterDomainToCharacterView().transform(getCharacterByIdUseCase.execute(id = id))
        }
    }

    fun getEpisodesList(ids: List<Int>) {
        viewModelScope.launch {
            _episodesList.value =
                getAllEpisodesByIdsUseCase.execute(ids = ids).map {
                    EpisodeDomainToEpisodeView().transform(it)
                }
        }
    }


}