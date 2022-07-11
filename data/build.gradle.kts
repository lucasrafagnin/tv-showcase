plugins {
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = 32
}

dependencies {

    implementation(project(":domain"))

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.moshi)
    implementation(libs.bundles.retrofit)
    implementation(libs.bundles.room)

    implementation(libs.hilt)
    implementation(libs.paging)
    implementation(libs.retrofitMoshi)

    kapt(libs.hiltCompiler)
    kapt(libs.roomCompiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockito)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.mockWebserver)
}
