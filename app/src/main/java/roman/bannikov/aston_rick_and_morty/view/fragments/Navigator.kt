package roman.bannikov.aston_rick_and_morty.view.fragments

import roman.bannikov.aston_rick_and_morty.models.CharacterModel

interface Navigator {

    fun showCharacterDetails(characterModel: CharacterModel)

    fun goBack()

    fun showToast (messageRes: Int)
}