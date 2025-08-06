plugins {
    id("com.codandotv.application")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.compose)
}

android {
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    val koin_version = "2.2.3"
    val lifecycle_version = "2.7.0"

    implementation(projects.craftdCore)
    implementation(projects.craftdXml)
    implementation(projects.craftdCompose)

    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.kotlinx.collections.immutable)

//    implementation("io.github.codandotv:craftd-xml:0.0.1") // revisar se é necessário junto com `projects.craftdXml`

    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-simplexml:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.4")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.4")
    implementation("com.squareup.picasso:picasso:2.8")

    implementation("io.insert-koin:koin-androidx-scope:$koin_version")
    implementation("io.insert-koin:koin-androidx-viewmodel:$koin_version")
    implementation("io.insert-koin:koin-androidx-fragment:$koin_version")
    implementation("io.insert-koin:koin-androidx-ext:$koin_version")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val composeBom = platform("androidx.compose:compose-bom:2024.02.02")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.material:material-icons-core")

    debugImplementation("androidx.compose.ui:ui-tooling")

    testImplementation(libs.junit)
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
