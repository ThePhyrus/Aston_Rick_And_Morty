package roman.bannikov.aston_rick_and_morty.viewmodel.location

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories.LocationSettingsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.data.storage.sharedPref.LocationSettingsPref
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsDimensionsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsTypesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.settings.LocationsSettingsUseCases

class LocationFilterViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val locationFilterRepositoryImpl by lazy {
        LocationFilterRepositoryImpl(db = db)
    }

    private val locationSettingsPref by lazy {
        LocationSettingsPref(context = context)
    }

    private val locationSettingsRepository by lazy {
        LocationSettingsRepositoryImpl(locationSettingsPref = locationSettingsPref)
    }


    private val getListLocationsDimensionsUseCase by lazy {
        GetListLocationsDimensionsUseCase(getLocationFiltersRepository = locationFilterRepositoryImpl)
    }

    private val getListLocationsTypesUseCase by lazy {
        GetListLocationsTypesUseCase(getLocationFiltersRepository = locationFilterRepositoryImpl)
    }

    private val locationSettingsUseCases by lazy {
        LocationsSettingsUseCases(locationSettingsRepository = locationSettingsRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationFilterViewModel(
            getListLocationsDimensionsUseCase = getListLocationsDimensionsUseCase,
            getListLocationsTypesUseCase = getListLocationsTypesUseCase,
            locationSettingsUseCases = locationSettingsUseCases
        ) as T
    }
}