android{
    buildFeatures{
        viewBinding =  true
    }
}

plugins {
    id("com.codandotv.android-library")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            api(projects.craftdCore)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.androidx.core)
            implementation(libs.androidx.appcompat)
            implementation(libs.google.material)
        }
    }
}