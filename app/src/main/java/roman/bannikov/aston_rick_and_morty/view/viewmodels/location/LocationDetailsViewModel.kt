package roman.bannikov.aston_rick_and_morty.view.viewmodels.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.details.GetLocationByIdUseCase
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