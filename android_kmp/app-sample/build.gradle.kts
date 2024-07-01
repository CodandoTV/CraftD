plugins {
    id("com.codandotv.application")
    alias(libs.plugins.jetbrains.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    val koin_version = "2.2.3"
    implementation(projects.craftdCore)
    implementation(projects.craftdXml)
    implementation(projects.craftdCompose)
    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("io.github.codandotv:craftd-xml:0.0.1")

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-simplexml:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.4")
    implementation("com.squareup.picasso:picasso:2.8")

    // Koin AndroidX Scope features
    implementation("io.insert-koin:koin-androidx-scope:$koin_version")
    // Koin AndroidX ViewModel features
    implementation("io.insert-koin:koin-androidx-viewmodel:$koin_version")
    // Koin AndroidX Fragment features
    implementation("io.insert-koin:koin-androidx-fragment:$koin_version")
    // Koin AndroidX Experimental features
    implementation("io.insert-koin:koin-androidx-ext:$koin_version")

    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1");

    val lifecycle_version = "2.7.0"
    // Lifecycle utilities for Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")

    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Choose one of the following:
    // Material Design 3
    implementation("androidx.compose.material3:material3")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")


    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    implementation("androidx.compose.material:material-icons-core")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.8.2")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation(libs.kotlinx.collections.immutable)
}