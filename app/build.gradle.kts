apply(from = "../jacoco/modules.gradle")

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.rafagnin.tvshowcase"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_URL", "\"https://api.tvmaze.com/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            enableUnitTestCoverage = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.moshi)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.room)

    implementation(libs.appCompat)
    implementation(libs.coil)
    implementation(libs.expandableText)
    implementation(libs.hilt)
    implementation(libs.liveData)
    implementation(libs.material)
    implementation(libs.paging)
    implementation(libs.retrofitMoshi)
    implementation(libs.viewModel)
    implementation(libs.splashScreen)

    kapt(libs.hiltCompiler)
    kapt(libs.roomCompiler)
    testImplementation(libs.junit)
}
