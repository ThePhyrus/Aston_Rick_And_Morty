package roman.bannikov.aston_rick_and_morty.listeners

import roman.bannikov.aston_rick_and_morty.models.CharacterModel

interface OnCharacterCardClickListener {
    //todo сделать функциональным, чтобы использовать ля-ля-лямбду
    fun launchCharacterDetailsFragment(character: CharacterModel)
}