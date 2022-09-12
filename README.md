
<p align="center"><img src="assets/img_app.png" width="80" /></p>
<h1 align="center">TV Showcase</h1></br>

<p align="center">
  <a href="https://github.com/lucasrafagnin/tv-showcase/actions"><img src="https://github.com/lucasrafagnin/tv-showcase/actions/workflows/master-ci.yml/badge.svg"/></a>
  <a href="https://www.codacy.com/gh/lucasrafagnin/tv-showcase/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lucasrafagnin/tv-showcase&amp;utm_campaign=Badge_Grade"><img alt="Google" src="https://app.codacy.com/project/badge/Grade/b84ef180a6cd423fb94861a227db5927"/></a>
  <a href="https://codecov.io/gh/lucasrafagnin/tv-showcase"><img src="https://codecov.io/gh/lucasrafagnin/tv-showcase/branch/master/graph/badge.svg?token=Y7P0LI9PUO"/></a>
</p>

<p align="center">
A TV Showcase 🍿 Android App using Jetpack libraries and MVVM architecture. Data provided by TVMaze API.<br>
</p>

![Cover](https://github.com/lucasrafagnin/tv-showcase/blob/master/assets/cover.jpg)

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
- [Ktlint 💅](https://github.com/pinterest/ktlint) - Kotlin linter
- [JUnit](https://developer.android.com/jetpack/androidx/releases/test?hl=pt-br), [Mockito](https://site.mockito.org/) and [Mockk](https://mockk.io/) for unit tests

## Features 🔖
- List of all series contained in the API
- Search option to find series by name
- Series detail page that shows details about the series, cast, seasons, and episodes
- Episode detail page
- Cast detail page
- Daily episode calendar
- Option to save to favorites

## Tech details 🧑‍🔬
- Day/Night theme for API level 29+
- Test coverage on Data module
- Test coverage on Domain module
- Shortcuts to main pages
- Adaptive icon
- Support to PT language
- Search by voice
