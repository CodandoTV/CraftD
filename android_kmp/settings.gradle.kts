@file:Suppress("UnstableApiUsage")
pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }

}
rootProject.name = "CraftD"
include(":app-sample")
include(":craftd-core")
include(":craftd-xml")
include(":craftd-compose")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")