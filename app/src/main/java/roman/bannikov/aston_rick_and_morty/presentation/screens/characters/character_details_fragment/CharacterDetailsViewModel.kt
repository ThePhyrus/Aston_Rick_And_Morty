package roman.bannikov.aston_rick_and_morty.presentation.screens.characters.character_details_fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.character_details_use_cases.GetCharacterByIdUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episodes_usecases.GetAllEpisodesByIdsUseCase
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetCharacterPresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetEpisodePresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.models.character.CharacterPresentation
import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val getAllEpisodesByIdsUseCase: GetAllEpisodesByIdsUseCase
) : ViewModel() {

    private val _characterDetails = MutableLiveData<CharacterPresentation>()
    val characterDetails: MutableLiveData<CharacterPresentation> = _characterDetails

    private val _episodesList = MutableLiveData<List<EpisodePresentation>>()
    val episodesList: MutableLiveData<List<EpisodePresentation>> = _episodesList

    fun getCharacter(id: Int) {
        viewModelScope.launch {
            _characterDetails.value =
                GetCharacterPresentationModel().transform(getCharacterByIdUseCase.execute(id = id))
        }
    }

    fun getEpisodesList(ids: List<Int>) {
        viewModelScope.launch {
            _episodesList.value =
                getAllEpisodesByIdsUseCase.execute(ids = ids).map {
                    GetEpisodePresentationModel().transform(it)
                }
        }
    }


}