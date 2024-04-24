plugins {
    id("com.codandotv.android-library")
}


dependencies {
    val lifecycle_version = "2.7.0"
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.fasterxml.jackson)
    implementation(libs.fasterxml.jackson.databind)
}