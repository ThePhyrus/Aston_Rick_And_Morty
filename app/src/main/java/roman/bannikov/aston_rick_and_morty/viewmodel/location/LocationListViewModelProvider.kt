package roman.bannikov.aston_rick_and_morty.viewmodel.location

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.remote.api.RetrofitInstance
import roman.bannikov.aston_rick_and_morty.data.repositories.locations_repositories.LocationsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.locations_usecases.GetAllLocationsUseCase

@ExperimentalPagingApi
class LocationListViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofitInstance by lazy {
        RetrofitInstance.locationsApi
    }

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val locationsRepository by lazy {
        LocationsRepositoryImpl(db = db, locationsApi = retrofitInstance)
    }

    private val getAllLocationsUseCase by lazy {
        GetAllLocationsUseCase(locationsRepository = locationsRepository)
    }


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationListViewModel(
            getAllLocationsUseCase = getAllLocationsUseCase
        ) as T
    }
}