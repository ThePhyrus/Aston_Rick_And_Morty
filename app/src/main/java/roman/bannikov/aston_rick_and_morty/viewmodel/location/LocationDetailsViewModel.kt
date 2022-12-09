package roman.bannikov.aston_rick_and_morty.viewmodel.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_details_use_cases.GetLocationByIdUseCase
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetCharacterPresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetLocationPresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.models.character.CharacterPresentation
import roman.bannikov.aston_rick_and_morty.presentation.models.location.LocationPresentation
import kotlinx.coroutines.launch

class LocationDetailsViewModel(
    private val getLocationByIdUseCase: GetLocationByIdUseCase,
    private val getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
) : ViewModel() {

    private val _locationDetails = MutableLiveData<LocationPresentation>()
    val locationDetails: MutableLiveData<LocationPresentation> = _locationDetails

    private val _charactersList = MutableLiveData<List<CharacterPresentation>>()
    val charactersList: MutableLiveData<List<CharacterPresentation>> = _charactersList

    fun getLocation(id: Int) {
        viewModelScope.launch {
            _locationDetails.value =
                GetLocationPresentationModel().transform(getLocationByIdUseCase.execute(id = id))
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