plugins {
    id("com.codandotv.kmp-library")
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.compose)
    id("com.codandotv.publish")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release", "debug")
    }
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core)
            implementation(libs.androidx.appcompat)
            implementation(libs.google.material)
            implementation(libs.fasterxml.jackson)
            implementation(libs.fasterxml.jackson.databind)
        }

        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.coroutines.core)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
        }
    }
}