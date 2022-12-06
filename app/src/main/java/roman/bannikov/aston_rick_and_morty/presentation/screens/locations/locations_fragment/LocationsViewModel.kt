package roman.bannikov.aston_rick_and_morty.presentation.screens.locations.locations_fragment

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map

import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.locations_usecases.GetAllLocationsUseCase
import roman.bannikov.aston_rick_and_morty.presentation.mapper.domain_model_to_presentation.GetLocationPresentationModel
import roman.bannikov.aston_rick_and_morty.presentation.models.location.LocationPresentation
import kotlinx.coroutines.flow.*


@ExperimentalPagingApi
class LocationsViewModel(
    private val getAllLocationsUseCase: GetAllLocationsUseCase
) : ViewModel() {


    private val _filteredTrigger = MutableLiveData<MutableMap<String, String?>>(
        mutableMapOf(
            "name" to null,
            "type" to null,
            "dimension" to null,
        )
    )

    val filteredTrigger: MutableLiveData<MutableMap<String, String?>> = _filteredTrigger

    private var _locationsFlow = MutableSharedFlow<PagingData<LocationPresentation>>()
    val locationsFlow = _locationsFlow

    init {
        getLocationsByParams(null, null, null)
    }

    fun getLocationsByParams(
        name: String?,
        type: String?,
        dimension: String?
    ) {
        getAllLocationsUseCase.execute(
            name = name,
            type = type,
            dimension = dimension
        ).onEach {
            _locationsFlow.emit(
                it.map { obj -> GetLocationPresentationModel().transform(obj) }
            )
        }.launchIn(viewModelScope)
    }
}