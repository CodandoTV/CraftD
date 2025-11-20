android{
    buildFeatures{
        viewBinding =  true
    }
}

plugins {
    id("com.codandotv.android-library")
    id("com.codandotv.publish-android")
}

dependencies {
    api(projects.craftdCore)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
}