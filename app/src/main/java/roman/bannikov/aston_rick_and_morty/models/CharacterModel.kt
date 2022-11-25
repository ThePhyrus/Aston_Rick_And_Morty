package roman.bannikov.aston_rick_and_morty.models

data class CharacterModel(
    val characterId : Int,
    val characterImage : String,
    val characterName : String,
    val characterSpecies : String,
    val characterGender : String,
    val characterStatus : String
)

/*
*  Название персонажа
    (name), Вид (species), статус (status), пол (gender) и картинку
    (image). Данный список должен поддерживать пагинацию.*/
