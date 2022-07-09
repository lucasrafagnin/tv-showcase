plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${libs.versions.gradle.get()}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${libs.versions.kotlin.get()}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${libs.versions.navigation.get()}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${libs.versions.hilt.get()}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${libs.versions.navigation.get()}")
    }
}

allprojects {

    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        google()
        mavenCentral()
    }
}
