plugins {
    id("com.codandotv.android-library")
}

dependencies {
    implementation(libs.compose.lifecycle)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.fasterxml.jackson)
    implementation(libs.fasterxml.jackson.databind)
}