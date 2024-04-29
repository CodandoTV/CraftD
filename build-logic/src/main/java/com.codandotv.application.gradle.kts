@file:Suppress("UnstableApiUsage")

import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
import extensions.setupCompose
import extensions.setupPackingOptions

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}
val catalog: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")


android {
    namespace = Config.applicationId

    setupCompileOptions()
    setupPackingOptions()
    setupAndroidDefaultConfig()
    setupCompose()

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        multiDexEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    android {
        buildFeatures {
            viewBinding = true
        }
    }
    
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }
}