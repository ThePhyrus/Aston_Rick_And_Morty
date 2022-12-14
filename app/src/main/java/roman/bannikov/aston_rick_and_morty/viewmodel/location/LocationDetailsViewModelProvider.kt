package roman.bannikov.aston_rick_and_morty.viewmodel.location

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import roman.bannikov.aston_rick_and_morty.data.api.Retrofit
import roman.bannikov.aston_rick_and_morty.data.repositories.character.CharacterRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.repositories.location.LocationDetailsRepositoryImpl
import roman.bannikov.aston_rick_and_morty.data.storage.room.db.RickAndMortyDatabase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.characters.characters_usecases.GetAllCharactersByIdsUseCase
import roman.bannikov.aston_rick_and_morty.domain.use_cases.locations.location_details_use_cases.GetLocationByIdUseCase

@ExperimentalPagingApi
class LocationDetailsViewModelProvider(
    context: Context
) : ViewModelProvider.Factory {

    private val retrofit by lazy {
        Retrofit
    }

    private val characterDetailsApi by lazy {
        retrofit.characterDetailsApi
    }

    private val locationDetailsApi by lazy {
        retrofit.locationDetailsApi
    }

    private val charactersApi by lazy {
        retrofit.characterApi
    }

    private val db by lazy {
        RickAndMortyDatabase(context = context)
    }

    private val locationDetailsRepository by lazy {
        LocationDetailsRepositoryImpl(locationDetailsApi = locationDetailsApi, db = db)
    }

    private val charactersRepository by lazy {
        CharacterRepositoryImpl(
            characterApi = charactersApi,
            characterDetailsApi = characterDetailsApi,
            db = db
        )
    }

    private val getLocationByIdUseCase by lazy {
        GetLocationByIdUseCase(locationDetailsRepository = locationDetailsRepository)
    }

    private val getAllCharactersByIdsUseCase by lazy {
        GetAllCharactersByIdsUseCase(characterRepository = charactersRepository)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LocationDetailsViewModel(
            getLocationByIdUseCase = getLocationByIdUseCase,
            getAllCharactersByIdsUseCase = getAllCharactersByIdsUseCase
        ) as T
    }
}