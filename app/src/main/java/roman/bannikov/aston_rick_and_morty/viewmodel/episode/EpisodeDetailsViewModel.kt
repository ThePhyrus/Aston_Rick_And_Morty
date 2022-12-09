package roman.bannikov.aston_rick_and_morty.viewmodel.episode

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.episodes.episode_details_use_cases.GetEpisodeByIdUseCase
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetCharacterPresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetEpisodePresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.models.character.CharacterPresentation
import roman.bannikov.aston_rick_and_morty.presentation.models.episode.EpisodePresentation
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel(
    private val getEpisodeByIdUseCase: GetEpisodeByIdUseCase,
    private val getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
): ViewModel() {

    private val _episodeDetails = MutableLiveData<EpisodePresentation>()
    val episodeDetails: MutableLiveData<EpisodePresentation> = _episodeDetails

    private val _charactersList = MutableLiveData<List<CharacterPresentation>>()
    val charactersList: MutableLiveData<List<CharacterPresentation>> = _charactersList

    fun getEpisode(id: Int) {
        viewModelScope.launch {
            _episodeDetails.value =
                GetEpisodePresentationModel().transform(getEpisodeByIdUseCase.execute(id = id))
        }
    }

    fun getEpisodesList(ids: List<Int>) {
        viewModelScope.launch {
            _charactersList.value =
                getAllCharactersByIdsUseCase.execute(ids = ids).map {
                    GetCharacterPresentationModel().transform(it)
                }
        }
    }
}