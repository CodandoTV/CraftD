@file:Suppress("UnstableApiUsage")
import extensions.getLibrary
import extensions.setupCompose

plugins {
    id("com.codandotv.android-library")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
   setupCompose()
}

//Note: In order to publish and appear in the pom the versions cannot be used BOM
dependencies {
    implementation(libs.getLibrary("compose.ui.tooling"))
    implementation(libs.getLibrary("compose.ui"))
    implementation(libs.getLibrary("compose.lifecycle"))
    implementation(libs.getLibrary("compose.material3"))
    debugImplementation(libs.getLibrary("compose.ui.tooling"))
}