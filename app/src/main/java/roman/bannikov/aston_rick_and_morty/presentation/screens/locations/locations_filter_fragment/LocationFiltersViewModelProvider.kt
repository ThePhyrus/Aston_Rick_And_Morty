package roman.bannikov.aston_rick_and_morty.presentation.screens.locations.locations_filter_fragment

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.locations_repositories.GetLocationFiltersRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories.LocationSettingsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.data.storage.sharedPref.LocationSettingsPref
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_filters_use_cases.GetListLocationsDimensionsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_filters_use_cases.GetListLocationsTypesUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.settings.LocationsSettingsUseCases

class LocationFiltersViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val getLocationFiltersRepositoryImpl by lazy {
        GetLocationFiltersRepositoryImpl(db = db)
    }

    private val locationSettingsPref by lazy {
        LocationSettingsPref(context = context)
    }

    private val locationSettingsRepository by lazy {
        LocationSettingsRepositoryImpl(locationSettingsPref = locationSettingsPref)
    }


    private val getListLocationsDimensionsUseCase by lazy {
        GetListLocationsDimensionsUseCase(getLocationFiltersRepository = getLocationFiltersRepositoryImpl)
    }

    private val getListLocationsTypesUseCase by lazy {
        GetListLocationsTypesUseCase(getLocationFiltersRepository = getLocationFiltersRepositoryImpl)
    }

    private val locationSettingsUseCases by lazy {
        LocationsSettingsUseCases(locationSettingsRepository = locationSettingsRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationFiltersViewModel(
            getListLocationsDimensionsUseCase = getListLocationsDimensionsUseCase,
            getListLocationsTypesUseCase = getListLocationsTypesUseCase,
            locationSettingsUseCases = locationSettingsUseCases
        ) as T
    }
}