package roman.bannikov.aston_rick_and_morty.view.viewmodels.location

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationFilterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.settings_repositories.LocationSettingsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.preferences.LocationSettingsPref
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsDimensionsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.filter.GetListLocationsTypesUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.settings.LocationsSettingsUseCases


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