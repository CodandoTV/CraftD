plugins {
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kotlin.compose)
    id("com.codandotv.android-library")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(projects.craftdCore)
            implementation(libs.androidx.core)
            implementation(libs.androidx.appcompat)
            implementation(libs.google.material)
            implementation(libs.kotlinx.collections.immutable)
        }

        commonMain.dependencies {
            implementation(compose.ui)
            implementation(compose.material3)
        }
    }
}