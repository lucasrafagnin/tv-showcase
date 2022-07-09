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

    implementation(libs.bundles.coroutines)
    implementation(libs.bundles.moshi)

    implementation(libs.hilt)

    kapt(libs.hiltCompiler)
    kapt(libs.roomCompiler)
    testImplementation(libs.junit)

    androidTestImplementation(libs.espresso)
    androidTestImplementation(libs.junit)
}
