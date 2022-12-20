package roman.bannikov.aston_rick_and_morty.view.viewmodels.location

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.map
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list.GetAllLocationsUseCase
import roman.bannikov.aston_rick_and_morty.view.mapper.LocationDomainToLocationView
import roman.bannikov.aston_rick_and_morty.view.models.location.LocationView


@ExperimentalPagingApi
class LocationListViewModel(
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

    private var _locationsFlow = MutableSharedFlow<PagingData<LocationView>>()
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
                it.map { obj -> LocationDomainToLocationView().transform(obj) }
            )
        }.launchIn(viewModelScope)
    }
}