plugins {
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin)
        classpath(libs.navigationArgs)
        classpath(libs.hiltGradle)
    }
}

allprojects {

    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    repositories {
        google()
        mavenCentral()
    }
}
