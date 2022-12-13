package roman.bannikov.aston_rick_and_morty.viewmodel.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_details_use_cases.GetLocationByIdUseCase
import roman.bannikov.aston_rick_and_morty.view.mapper.CharacterDomainToCharacterView
import roman.bannikov.aston_rick_and_morty.view.mapper.LocationDomainToLocationView
import roman.bannikov.aston_rick_and_morty.view.models.character.CharacterView
import roman.bannikov.aston_rick_and_morty.view.models.location.LocationView

class LocationDetailsViewModel(
    private val getLocationByIdUseCase: GetLocationByIdUseCase,
    private val getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
) : ViewModel() {

    private val _locationDetails = MutableLiveData<LocationView>()
    val locationDetails: MutableLiveData<LocationView> = _locationDetails

    private val _charactersList = MutableLiveData<List<CharacterView>>()
    val charactersList: MutableLiveData<List<CharacterView>> = _charactersList

    fun getLocation(id: Int) {
        viewModelScope.launch {
            _locationDetails.value =
                LocationDomainToLocationView().transform(getLocationByIdUseCase.execute(id = id))
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