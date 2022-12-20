package roman.bannikov.aston_rick_and_morty

import android.app.Application
import roman.bannikov.aston_rick_and_morty.models.CharacterModelService

class App : Application() {

    val characterModelService = CharacterModelService()

}