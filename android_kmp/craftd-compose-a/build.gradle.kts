plugins {
    id("com.codandotv.android-library")
    id("com.codandotv.publish")
    alias(libs.plugins.jetbrains.compose)
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

/**
 * Compose metrics
 *
 * 1. First step is sync + build the project, this will generate the compose metrics;
 *
 * 2. Download [Mendable](https://github.com/jayasuryat/mendable) and run the following command:
 * java -jar mendable.jar --scanRecursively -i <work directory>/android_kmp/craftd-compose-a/build/compose_metrics -o <work directory>/android_kmp/craftd-compose-a/reports -oName craftDComposeMetrics -eType html -rType all
 * You can generate the compose metrics report using the following task:
 *
 * 3. Open the html file generated : )
 *
 * To be able to see a beutiful report, the community recommends to use [Mendable](https://github.com/jayasuryat/mendable).
 *
 * You can check the metrics in the directory <this module>/build/compose_metrics.
 *
 */
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:reportsDestination=" +
                project.buildDir.absolutePath + "/compose_metrics"
    )
    kotlinOptions.freeCompilerArgs += listOf(
        "-P",
        "plugin:androidx.compose.compiler.plugins.kotlin:metricsDestination=" +
                project.buildDir.absolutePath + "/compose_metrics"
    )
}