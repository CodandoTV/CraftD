@file:Suppress("UnstableApiUsage")
import extensions.getBundle
import extensions.getLibrary
import extensions.setupCompose
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

plugins {
    id("com.codandotv.android-library")
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

android {
   setupCompose()
}

dependencies {
    implementation(platform(libs.getLibrary("compose.bom")))
    androidTestImplementation(platform(libs.getLibrary("compose.bom")))

    implementation(libs.getBundle("compose"))
    debugImplementation(libs.getLibrary("compose.ui.tooling"))
}