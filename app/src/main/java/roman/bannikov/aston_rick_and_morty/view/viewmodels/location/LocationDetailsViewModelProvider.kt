package roman.bannikov.aston_rick_and_morty.view.viewmodels.location

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.database.AppDatabase
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.usecases.locations.details.GetLocationByIdUseCase

@ExperimentalPagingApi
class LocationDetailsViewModelProvider(
    private val getLocationByIdUseCase: GetLocationByIdUseCase,
    private val getAllCharactersByIdsUseCase: GetAllCharactersByIdsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationDetailsViewModel(
            getLocationByIdUseCase = getLocationByIdUseCase,
            getAllCharactersByIdsUseCase = getAllCharactersByIdsUseCase
        ) as T
    }
}