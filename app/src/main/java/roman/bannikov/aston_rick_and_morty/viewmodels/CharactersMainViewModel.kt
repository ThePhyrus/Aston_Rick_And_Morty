package roman.bannikov.aston_rick_and_morty.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import roman.bannikov.aston_rick_and_morty.models.CharacterModel
import roman.bannikov.aston_rick_and_morty.models.CharacterModelListener
import roman.bannikov.aston_rick_and_morty.models.CharacterModelService

class CharactersMainViewModel(
    private val characterModelService: CharacterModelService
) : ViewModel() {
    //В этом классе можно и нужно:
    // описать операции, которые разрешено делать из фрагмента
    // определить данные, которые вью-модель будет отправлять во фрагмент

    //определим данные:
    //Приватное свойство для самой вью-модели
    private val _charactersLiveData = MutableLiveData<List<CharacterModel>>()

    //Свойство, к которому будет иметь доступ вью (фрагмент будет иметь доступ будет именно к нему)
    val charactersLiveData: LiveData<List<CharacterModel>> = _charactersLiveData


    init {
        loadCharacters()
    }



    //определим действия:
    fun loadCharacters() {
        //получить данные о персонажах по ссылке (см. README.md)...
        //но пока просто исползуем Faker

    }


    //чтобы подписаться на получение персонажей из модели (на фейки)
    //(typealias in CharacterModelService)
    private val listener:CharacterModelListener = {
        _charactersLiveData.value = it
    }

    override fun onCleared() {
        super.onCleared()
        characterModelService.removeListener(listener) //чтобы не было утечек памяти
    }


}