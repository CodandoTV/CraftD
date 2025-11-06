@file:Suppress("UnstableApiUsage")

import Config.jvmTargetTool
import Config.jvmTargetValue
import Config.jvmTargetVersion
import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
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

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName
        multiDexEnabled = true
    }

    kotlin {
        jvmToolchain(jvmTargetTool)
    }

    compileOptions {
        sourceCompatibility = jvmTargetVersion
        targetCompatibility = jvmTargetVersion
    }

    android {
        buildFeatures {
            viewBinding = true
        }
    }
}