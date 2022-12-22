package roman.bannikov.aston_rick_and_morty.view.viewmodels.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsDimensionsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsTypesUseCase



class LocationFilterViewModelProvider(
    private val getListLocationsDimensionsUseCase: GetListLocationsDimensionsUseCase,
    private val getListLocationsTypesUseCase: GetListLocationsTypesUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationFilterViewModel(
            getListLocationsDimensionsUseCase = getListLocationsDimensionsUseCase,
            getListLocationsTypesUseCase = getListLocationsTypesUseCase,
        ) as T
    }
}