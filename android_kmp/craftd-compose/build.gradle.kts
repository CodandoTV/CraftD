plugins {
    id("com.codandotv.compose")
    id("com.codandotv.android-library")
    id("com.codandotv.publish")
}

dependencies {
    implementation(projects.craftdCore)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
}