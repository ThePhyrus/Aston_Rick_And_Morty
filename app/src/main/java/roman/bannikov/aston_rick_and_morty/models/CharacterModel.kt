package roman.bannikov.aston_rick_and_morty.models

data class CharacterModel(
    var characterId : Int,
    var characterImage : String,
    var characterName : String,
    var characterSpecies : String,
    var characterGender : String,
    var characterStatus : String
)

/*
*  Название персонажа
    (name), Вид (species), статус (status), пол (gender) и картинку
    (image). Данный список должен поддерживать пагинацию.*/
