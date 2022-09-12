apply(from = "jacoco/project.gradle")

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
        classpath(libs.jacoco)
    }
}

allprojects {

    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "jacoco")

    repositories {
        google()
        mavenCentral()
    }
}
