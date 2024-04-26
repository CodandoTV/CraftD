package extensions

import org.gradle.api.artifacts.VersionCatalog

internal fun VersionCatalog.getLibrary(library: String) = findLibrary(library).get()
internal fun VersionCatalog.getVersion(library: String) = findVersion(library).get()
internal fun VersionCatalog.getBundle(bundle: String) = findBundle(bundle).get()