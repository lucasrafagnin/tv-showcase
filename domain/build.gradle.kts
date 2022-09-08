plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
    }
}

dependencies {

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.moshi)

    implementation(libs.hilt)
    implementation(libs.paging)

    kapt(libs.hiltCompiler)
    kapt(libs.roomCompiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockk)
}
