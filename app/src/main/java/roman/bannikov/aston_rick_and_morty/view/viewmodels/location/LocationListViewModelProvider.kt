package roman.bannikov.aston_rick_and_morty.view.viewmodels.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list.GetAllLocationsUseCase

@ExperimentalPagingApi
class LocationListViewModelProvider(
    private val getAllLocationsUseCase: GetAllLocationsUseCase
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationListViewModel(
            getAllLocationsUseCase = getAllLocationsUseCase
        ) as T
    }
}