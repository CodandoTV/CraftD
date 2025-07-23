# Setup

## Android

- Add in your settings.gradle mavenCetral:

```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        //Stuff
        mavenCentral()
    }
}
```

- There are three versions for Android

!!! example "Jetpack Compose"

    ```kotlin
    implementation("io.github.codandotv:craftd-compose:${last_version}")
    ```

!!! example "XML View System"

    ```kotlin
    implementation("io.github.codandotv:craftd-xml:${last_version}")
    ```

!!! example "Core"
    Core - Is meant to be used for you to customize even the craftD mechanism

    ```kotlin
    implementation("io.github.codandotv:craftd-core:${last_version}")
    ```

## iOS

- Add your pod ‘CraftDSwiftUI’

!!! warning "In Progress"
    This section is in progress...

## Flutter

- Run this command with Flutter:

```shell
flutter pub add craftd_widget
```

This will add a line like this to your package's pubspec.yaml (and run an implicit flutter pub get):

!!! example "pubspec.yml"
    ```yaml
    dependencies:
        craftd_widget: $last_version
    ```