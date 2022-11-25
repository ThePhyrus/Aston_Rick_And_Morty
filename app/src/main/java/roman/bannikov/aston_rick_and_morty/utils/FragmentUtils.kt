package roman.bannikov.aston_rick_and_morty.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import roman.bannikov.aston_rick_and_morty.App
import roman.bannikov.aston_rick_and_morty.view.fragments.Navigator
import roman.bannikov.aston_rick_and_morty.viewmodels.CharacterDetailsViewModel
import roman.bannikov.aston_rick_and_morty.viewmodels.CharactersMainViewModel

//фаил FragmentUtils.kt будет содержит в себе классы и методы для работы с фрагментами.

/**
Этот класс (ViewModelFactory()) нужен потому, что в CharactersMainViewModel.kt я создал
конструктор НЕ по умолчанию.
Задача ViewModelFactory() создавать вью-модели с нужными параметрами, которые передаются в
конструктор. Так как обычно во вью-модель передаются в качестве параметров каки-то классы из
следующего слоя (из слоя модели), то фабрике нужно знать, где искать эти классы в слое модели.
Пока (в данном приложении) такой класс один и он синглтон (CharacterModelService()).
Находится он (его реализация?) в классе App().
Если будут добавляться ещё какие-нибудь классы (например LocationMainService), то я их буду
добавлять в класс App(). Поэтому, в приципе, в конструктор этой фабрики можно передать только
класс App()
Наследоваться ViewModelFactory() должен от ViewModelProvider.Factory!!!
 */
class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {

    //В аргументе приходит класс вью-модели, а возвращает саму (созданную тут вью-модель)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //пока вью-модель только одна
        val viewModel = when (modelClass) {
            CharactersMainViewModel::class.java -> {
                CharactersMainViewModel(app.characterModelService)
            }
            //Появилась вторая вью-модель (делаем по аналогии)
            CharacterDetailsViewModel::class.java -> {
                CharacterDetailsViewModel(app.characterModelService)
            }
            else -> {
                //Бросим исключение, если придёт какая-то непонятная дичь
                throw java.lang.IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T //fixme unchecked cast
    }
}

/**
Эта функция будет доступна во всех фрагментах и нужна для того, чтобы тыщщщу раз не писать
один и тотже код
 */
fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)


/**
Функция для получения навигатора. Теперь везде, где идут переходы между экранами, можно
обратиться к навигатору и добавить соответсвующую логику
 */
fun Fragment.navigator() = requireActivity() as Navigator
