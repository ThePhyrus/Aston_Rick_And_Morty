package roman.bannikov.aston_rick_and_morty.view.viewmodels.location

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.list.GetAllLocationsUseCase

@ExperimentalPagingApi
class LocationListViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofitInstance by lazy {
        Retrofit.locationApi
    }

    private val db by lazy {
        AppDatabase(context = context)
    }

    private val locationsRepository by lazy {
        LocationRepositoryImpl(db = db, locationApi = retrofitInstance)
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