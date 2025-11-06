@file:Suppress("UnstableApiUsage")

import Config.jvmTargetValue
import extensions.setupAndroidDefaultConfig
import extensions.setupCompileOptions
import extensions.setupNameSpace
import extensions.setupPackingOptions
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.multiplatform")
}

android {
    setupNameSpace(project)

    setupCompileOptions()

    setupPackingOptions()

    setupAndroidDefaultConfig()
    defaultConfig.targetSdk = Config.targetSdkVersion

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles("proguard-rules.pro")
            consumerProguardFiles("proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(jvmTargetValue)
            freeCompilerArgs.add("-Xstring-concat=inline")
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
}