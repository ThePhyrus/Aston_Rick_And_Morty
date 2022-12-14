# Aston_Rick_And_Morty

Rick and Morty

Техническое описание.

    Содержание:

    1 - Техническое задание для курсового проекта от Aston
        1.1 - Общая информация
        1.2 - Splash screen
        1.3 - Основной экран
        1.4 - Вкладка с персонажами
        1.5 - Вкладка с эпизодами
        1.6 - Вкладка с локациями
        1.7 - Фильтры
        1.8 - Детали персонажа
        1.9 - Детали локации
        1.X - Детали эпизода
    2 - Визуальное представление UI (попробую использовать Figma, но я с ней пока не знаком)
    3 - Описание user-flow
    4 - Описание функционала
    5 - Список сторонних библиотек
        5.1 - Используемые библиотеки
        5.2 - Библиотеки, запрещённые к использованию
    6 - Что использовал при создании приложения

Часть 1: Техническое задание для курсового проекта от Aston

    1.1 - Общая информация
    Основной ресурс для API:
    https://rickandmortyapi.com/documentation/#introduction
    Приложение должно поддерживать кеширование и иметь
    возможность работать без интернета.
    Весь функционал по поиску и фильтрации также должен
    поддерживать работу без интернета.
    Приложение должно поддерживать навигацию назад. На всех
    экранах, кроме главного, необходимо отображать стрелку назад.
    Если после выполнения запроса, данных не оказалось, то
    необходимо показывать соответствующий текст пользователю
    (возможно при поиске или фильтрации).
    Все вкладки должны поддерживать Pull-to-Refresh.
    В момент загрузки данных необходимо отображать
    прогресс-индикатор.
    Библиотеки из следующего списка использовать запрещается:
    https://rickandmortyapi.com/documentation/#libraries

    1.2 - Splash screen
    При открытии приложения должен показываться Splash экран. Он
    должен отображаться как фон для системного Window. Это должна
    быть картинка или же текст символизирующий приложение.

    1.3 - Основной экран
    Основной экран должен содержать нижнюю навигацию с 3
    вкладками. Вкладки должны быть следующими:
        персонажи;
        локации;
        эпизоды.
      После запуска приложения, первым должна отображаться вкладка с
      персонажами.
      На каждой вкладке должен быть доступ к поиску по данной вкладке,
      а также возможности отфильтровать.
      Фильтрация должна быть выполнена в соответствии с описанием
      запроса для каждой вкладки.
      https://rickandmortyapi.com/documentation/#filter-characters
      https://rickandmortyapi.com/documentation/#filter-locations
      https://rickandmortyapi.com/documentation/#filter-episodes
      Нажатие на элемент из списка должно открывать экран с деталями
      выбранного объекта. Персонаж - детали персонажа, локация -
      детали локации, эпизод - детали эпизода.
    
        1.4 - Вкладка с персонажами
    Данная вкладка должна содержать список всех персонажей,
    которые могут быть получены с помощью следующего запроса:
    https://rickandmortyapi.com/documentation/#get-all-characters
    Список необходимо выполнить в виде таблицы с 2 столбцами.
    Каждый элемент списка должен содержать Название персонажа
    (name), Вид (species), статус (status), пол (gender) и картинку
    (image). Данный список должен поддерживать пагинацию.
    
        1.5 - Вкладка с эпизодами
    Данная вкладка должна содержать список всех персонажей,
    которые могут быть получены с помощью следующего запроса:
    https://rickandmortyapi.com/documentation/#get-all-episodes
    Список необходимо выполнить в виде таблицы с 2 столбцами.
    Каждый элемент списка должен содержать Название эпизода
    (name), номер эпизода (episode) и дату релиза (air_date). Данный
    список должен поддерживать пагинацию.
    
        1.6 - Вкладка с локациями
    Данная вкладка должна содержать список всех персонажей,
    которые могут быть получены с помощью следующего запроса:
    https://rickandmortyapi.com/documentation/#get-all-locations
    Список необходимо выполнить в виде таблицы с 2 столбцами.
    Каждый элемент списка должен содержать Название локации
    (name), тип (type) и измерение (dimension). Данный список
    должен поддерживать пагинацию.
    
        1.7 - Фильтры
    Экран с фильтрами будет различным для разного типа контента.
    Он должен содержать опции для фильтрации, а также кнопку для
    применения фильтра.
    Все возможные способы применения фильтров можно обнаружить
    тут:
      https://rickandmortyapi.com/documentation/#filter-characters
      https://rickandmortyapi.com/documentation/#filter-locations
      https://rickandmortyapi.com/documentation/#filter-episodes
    
        1.8 - Детали персонажа
    Детали о персонаже возможно получить с помощью следующего
    запроса:
    https://rickandmortyapi.com/documentation/#get-a-single-character
    На данном экране необходимо отобразить всю информацию,
    которая приходит нам с сервера и может быть полезна
    пользователю.
    Список эпизодов необходимо выполнить в формате списка с 1
    столбцом и элементами содержащими те же данные, что и на
    вкладке с эпизодами.
    При нажатии на эпизод необходимо отобразить детали выбранного
    эпизода.
    При нажатии на локацию (location) или же место происхождения
    (origin) необходимо открывать детали выбранной локации.
    
        1.9 - Детали локации
    Детали о локации возможно получить с помощью следующего
    запроса:
    https://rickandmortyapi.com/documentation/#get-a-single-location
    На данном экране необходимо отобразить всю информацию,
    которая приходит нам с сервера и может быть полезна
    пользователю.
    Список персонажей должен быть выполнен в виде таблицы с 2
    столбцами, как это сделано в вкладке с персонажами.
    При нажатии на персонажа необходимо открывать детали
    персонажа.
    
        1.X - Детали эпизода
    Детали о локации возможно получить с помощью следующего
    запроса:
    https://rickandmortyapi.com/documentation/#get-a-single-episode
    На данном экране необходимо отобразить всю информацию,
    которая приходит нам с сервера и может быть полезна
    пользователю.
    Список персонажей должен быть выполнен в виде таблицы с 2
    столбцами, как это сделано в вкладке с персонажами.
    При нажатии на персонажа необходимо открывать детали
    персонажа.


Часть 3: Описание user-flow![Flow](https://user-images.githubusercontent.com/88384527/209301349-838fcb95-55e7-4a7e-8205-4224cb4aeb19.jpg)


    При запуске приложения пользователь в течении 2 секунд видит SplashActivity с картинкой, 
    символизирующей приложение. Затем он попадает на главный экран, на котором отображается полный
    список персонажей полученный по запросу: 
    https://rickandmortyapi.com/documentation/#get-all-characters и нижнюю навигационную панель
    (bottomNavigationView) с тремя вкладками: персонажи, эпизоды, локации, которые открывают 
    соответсвующие экраны. Основная навигация видна на картинке выше. Навигация с экранов локаций и
    эпизодов выполнена по аналогии.



Часть 5: Список сторонних библиотек

    5.1 - Используемые библиотеки:
            // Glide
    implementation "com.github.bumptech.glide:glide:4.12.0"
    // PullToRefresh
    implementation "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    // Flow
    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.5.1'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.5.1'
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
    // Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4'
    // Paging
    implementation "androidx.paging:paging-runtime-ktx:3.1.1"
    // Room
    implementation "androidx.room:room-runtime:2.4.3"
    implementation "androidx.room:room-ktx:2.4.3"
    implementation "androidx.room:room-paging:2.4.3"
    kapt "androidx.room:room-compiler:2.4.3"
    // Recyclerview
    implementation "androidx.recyclerview:recyclerview:1.3.0-rc01"
    // Retrofit
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3"
    // Gson
    implementation 'com.google.code.gson:gson:2.8.9'
    // Fragments
    implementation 'androidx.fragment:fragment-ktx:1.5.5'
    // ViewModel
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1'
    implementation "androidx.lifecycle:lifecycle-extensions:2.2.0"
    // Dagger 2
    implementation 'com.google.dagger:dagger:2.40.5'
    kapt 'com.google.dagger:dagger-compiler:2.40.5'

    5.2 - Библиотеки, запрещённые к использованию:

        Dart
        Rick and Morty API Dart Client by Yash Garg
        Elixir
        ExShla - The Rick and Morty API Wrapper by l1h3r
        Go
        The Rick and Morty API Go client by Leopoldo Caballero
        Java
        Rick and Morty API Java Client by Adriano Rocha 
        .NET
        Rick.Net by BIGDummyHead
        RickAndMorty.Net.Api by Carlj28
        PHP
        Rick and Morty API PHP Client by Nick Been 
        Python
        Python implementation for the Rick and Morty API by Rohan Hazra
        R
        mortyr by Mike Page 
        Ruby
        The Rick and Morty API Gem by Tommy Spielhoelle
        Rust
        rick-and-morty crate by dshomoye
        Swift
        The Rick and Morty API Swift Client by Benjamin Bruch

Часть 6: Что использовал при создании приложения

    1 - Проект содержит SplashActivity
    2 - Реализована bottomNavigationView
    3 - View Binding
    4 - Фунцкция Pull-to-refresh
    5 - Coroutine (Flow, LiveData)
    6 - ListAdapter
    7 - Room
    8 - Библеотека Paging 3
    9 - Dagger

