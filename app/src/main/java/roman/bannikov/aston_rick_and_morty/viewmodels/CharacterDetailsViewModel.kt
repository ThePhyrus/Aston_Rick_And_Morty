package roman.bannikov.aston_rick_and_morty.viewmodels

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import roman.bannikov.aston_rick_and_morty.models.CharacterDetailsModel
import roman.bannikov.aston_rick_and_morty.models.CharacterModel
import roman.bannikov.aston_rick_and_morty.models.CharacterModelService
import roman.bannikov.aston_rick_and_morty.utils.CharacterNotFoundException

class CharacterDetailsViewModel(
    private val characterModelService: CharacterModelService
) : ViewModel() {

    //определим данные:
    //Приватное свойство для самой вью-модели
    private val _characterDetailsLiveData = MutableLiveData<CharacterDetailsModel>()

    //Свойство, к которому будет иметь доступ вью (фрагмент будет иметь доступ будет именно к нему)
    val characterDetailsLiveData: LiveData<CharacterDetailsModel> = _characterDetailsLiveData

    //определим действия:
    fun showCharacterDetails(characterId: Int) {
        if (_characterDetailsLiveData.value != null) return
        try {
            _characterDetailsLiveData.value =
                characterModelService.getCharacterById(id = characterId)
        } catch (e: CharacterNotFoundException) {
            e.printStackTrace()
        }
    }


}