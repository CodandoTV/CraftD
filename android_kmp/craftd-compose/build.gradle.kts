plugins {
    id("com.codandotv.compose")
    id("com.codandotv.android-library")
    id("com.codandotv.publish")
}

dependencies {
    implementation(projects.craftdCore)
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
}

/**
 * Compose metrics
 *
 * You can generate the compose metrics report using the following task:
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