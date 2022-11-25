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
    //Цель и задачи вью-модели: выполнять запросы к модели, получать данные из этой модели и
    //преобразовывать их в удобный вид для того, чтобы слой вью мог эти данные отрисовать на экране.

    //В этом классе можно и нужно:
    // описать операции, которые разрешено делать из фрагмента
    // определить данные, которые вью-модель будет отправлять во фрагмент

    //определим данные:
    //Приватное свойство для самой вью-модели
    private val _charactersLiveData = MutableLiveData<List<CharacterModel>>()

    //Свойство, к которому будет иметь доступ вью (фрагмент будет иметь доступ будет именно к нему)
    val charactersLiveData: LiveData<List<CharacterModel>> = _charactersLiveData



    //Слушатель, чтобы подписаться на получение персонажей из модели (на фейки)
    //(typealias in CharacterModelService)
    //Если есть слушатель, который вызывается в блоке init, то слушатель надо писать
    //перед блоком init
    private val listener:CharacterModelListener = {
        _charactersLiveData.value = it
    }


    init {
        loadCharacters()
    }


    //определим действия: (делигировали их куда-то там... куда? к следующему слою - к модели,
    // точнее - к методам класса CharacterModelService() в слое model)
    fun loadCharacters() {
        //получить данные о персонажах по ссылке (см. README.md)...
        //но пока просто исползуем Faker
        characterModelService.addListener(listener)

    }




    override fun onCleared() {
        super.onCleared()
        characterModelService.removeListener(listener) //чтобы не было утечек памяти
    }


}