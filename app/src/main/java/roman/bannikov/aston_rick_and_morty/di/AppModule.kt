package roman.bannikov.aston_rick_and_morty.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import dagger.Module
import dagger.Provides
import roman.bannikov.aston_rick_and_morty.domain.usecases.character.list.GetAllCharactersUseCase
import roman.bannikov.aston_rick_and_morty.view.viewmodels.character.CharacterListViewModelProvider
import javax.inject.Singleton


@ExperimentalPagingApi
@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context{
        return context
    }

    @Provides
    @Singleton
    fun provideCharacterListViewModelProvider(
        getAllCharactersUseCase: GetAllCharactersUseCase
    ): CharacterListViewModelProvider {
        return CharacterListViewModelProvider(
            getAllCharactersUseCase = getAllCharactersUseCase
        )
    }


/*    @Provides
    @Singleton
    fun provideCharactersViewModelProvider(
        getAllCharactersUseCase: GetAllCharactersUseCase
    ): CharactersViewModelProvider {
        return CharactersViewModelProvider(
            getAllCharactersUseCase = getAllCharactersUseCase
        )
    }*/
}