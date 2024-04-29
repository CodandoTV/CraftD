@file:Suppress("UnstableApiUsage")

package extensions

import Config
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun CommonExtension<*, *, *, *, *>.setupPackingOptions() {
    packaging {
        resources {
            with(pickFirsts) {
                add("META-INF/library_release.kotlin_module")
                add("META-INF/LICENSE.md")
                add("META-INF/LICENSE-notice.md")
            }
            with(excludes) {
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
            }
        }
    }
}

internal fun CommonExtension<*, *, *, *, *>.setupAndroidDefaultConfig() {
    defaultConfig {
        compileSdk = Config.compileSdkVersion
        minSdk = Config.minSdkVersion
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = Config.testInstrumentationRunner
    }
}

internal fun CommonExtension<*, *, *, *, *>.setupCompileOptions() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

fun CommonExtension<*, *, *, *, *>.setupCompose() {
    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.7"
    }

    packaging {
        resources {
            excludes.apply {
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
            }
        }
    }
}


internal fun CommonExtension<*, *, *, *, *>.setupNameSpace(project: Project) {
    val moduleName = project.displayName
        .removePrefix("project ")
        .replace(":", ".")
        .replace("'", "")
        .replace("-", ".")

    namespace = "${Config.applicationId}$moduleName"
}

private fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}
