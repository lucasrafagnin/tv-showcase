
<p align="center"><img src="assets/img_app.png" width="80" /></p>
<h1 align="center">TV Showcase</h1></br>

<p align="center">  
A TV Showcase 🍿 Android App using Jetpack libraries and MVVM architecture. Data provided by TVMaze API.<br>
</p>
<br>

## Release 🚀
- Download [here](https://github.com/lucasrafagnin/tv-showcase/releases/tag/v1.0)
- See how the app looks like 📹 [here](https://www.youtube.com/watch?v=hSYgi45N5zY)

## Tech Stack 🛠
- Minimum SDK API level: 21
- [Kotlin](https://kotlinlang.org/) - Official programming language for Android development.
- [Dagger Hilt](https://dagger.dev/hilt/) - Dependency Injection library for Android
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous stuff and more.
- [Flow](https://developer.android.com/kotlin/flow) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
- [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Manages UI data and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
- [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Bind UI components from layouts to data sources in app using a declarative format rather than programmatically.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android.
- [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Navigation](https://developer.android.com/guide/navigation) - A library that helps navigation between fragments
- [Coil-kt](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin Coroutines.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing Gradle build scripts using Kotlin.
- [Ktlin 💅](https://github.com/pinterest/ktlint) - Kotlin linter

## Features
- [X] List all of the series contained in the API used by the paging scheme provided by the API.
- [X] Allow users to search series by name.
- [X] The listing and search views must show at least the name and poster image of the series.
- [X] After clicking on a series, the application should show the details of the series, showing the following information:
    - [X] Name
    - [X] Poster
    - [X] Days and time during which the series airs
    - [X] Genres
    - [X] Summary
    - [X] List of episodes separated by season
- [X] After clicking on an episode, the application should show the episode’s information, including:
    - [X] Name
    - [X] Number
    - [X] Season
    - [X] Summary
    - [X] Image, if there is one
    
## Optional Features
- [ ] Allow the user to set a PIN number to secure the application and prevent unauthorized users.
- [ ] For supported phones, the user must be able to choose if they want to enable fingerprint authentication to avoid typing the PIN number while opening the app.
- [X] Allow the user to save a series as a favorite.
- [X] Allow the user to delete a series from the favorites list.
- [X] Allow the user to browse their favorite series in alphabetical order, and click on one to see its details.
- [ ] Create a people search by listing the name and image of the person.
- [X] After clicking on a person, the application should show the details of that person, such as:
    - [X] Name
    - [X] Image
    - [ ] Series they have participated in, with a link to the series details.

## Bonus
- [X] Episodes schedule of the day
- [X] Show rating
- [X] Shortcuts to home, schedule and favorites
- [X] Adaptive icon
- [X] Day/Night theme for API level 29+
- [X] Support to PT language
- [X] Search by voice
