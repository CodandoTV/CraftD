plugins {
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.compose)
    id("com.codandotv.kmp-library")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.core)
            implementation(libs.androidx.appcompat)
            implementation(libs.google.material)
        }

        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
            api(projects.craftdCore)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(libs.kotlinx.collections.immutable)
        }
    }
}