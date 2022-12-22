package roman.bannikov.aston_rick_and_morty.view.viewmodels.location


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsDimensionsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsTypesUseCase



class LocationFilterViewModel(
    private val getListLocationsDimensionsUseCase: GetListLocationsDimensionsUseCase,
    private val getListLocationsTypesUseCase: GetListLocationsTypesUseCase,
) : ViewModel() {

    private val _dimensionList = MutableStateFlow<List<String>>(listOf())
    val dimensionList: StateFlow<List<String>> = _dimensionList

    private val _typeList = MutableStateFlow<List<String>>(listOf())
    val typeList: StateFlow<List<String>> = _typeList

    init {
        viewModelScope.launch {
            getListLocationsDimensionsUseCase.execute()
                .collect { list ->
                    _dimensionList.value = list
                }
        }

        viewModelScope.launch {
            getListLocationsTypesUseCase.execute()
                .collect { list ->
                    _typeList.value = list
                }
        }
    }

}