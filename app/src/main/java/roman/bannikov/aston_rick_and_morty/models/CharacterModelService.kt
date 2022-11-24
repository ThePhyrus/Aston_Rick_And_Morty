package roman.bannikov.aston_rick_and_morty.models

import com.github.javafaker.Faker
import java.util.*

typealias CharacterModelListener = (characters: List<CharacterModel>) -> Unit

class CharacterModelService {
    private var mlCharacters = mutableListOf<CharacterModel>()
    private val listeners = mutableSetOf<CharacterModelListener>()

    init {
        val faker = Faker.instance()
        mlCharacters = (1..100).map {
            CharacterModel(
                characterId = it.toInt(),
                characterImage = RANDOM_IMAGE_URL,
                characterName = faker.name().name(),
                characterGender = faker.funnyName().name(),
                characterSpecies = "Species",
                characterStatus = "status"

            )
        }.toMutableList()
    }

    fun addListener(listener: CharacterModelListener) {
        listeners.add(listener)
        listener.invoke(mlCharacters)
    }

    fun removeListener(listener: CharacterModelListener) {
        listeners.remove(listener)
    }


    fun getCharacters(): List<CharacterModel> {
        return mlCharacters
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(mlCharacters) }
    }

    fun moveCharacter(character: CharacterModel, moveBy: Int) {
        val oldIndex = mlCharacters.indexOfFirst { it.characterId == character.characterId }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= mlCharacters.size) return
        Collections.swap(mlCharacters, oldIndex, newIndex)
        notifyChanges()
    }


    companion object {
        private const val RANDOM_IMAGE_URL = "https://picsum.photos/200/300?random"
    }
}