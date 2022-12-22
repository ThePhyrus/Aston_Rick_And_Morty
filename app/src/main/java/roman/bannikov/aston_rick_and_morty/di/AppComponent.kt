package roman.bannikov.aston_rick_and_morty.di

import androidx.paging.ExperimentalPagingApi
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import roman.bannikov.aston_rick_and_morty.view.fragments.character.CharacterListFragment
import javax.inject.Singleton



@ExperimentalCoroutinesApi
@ExperimentalPagingApi



@Singleton
@Component(modules = [AppModule::class])

interface AppComponent {
    fun inject(characterListFragment: CharacterListFragment)
}