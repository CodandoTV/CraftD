plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

tasks.register("exportCraftdComposeXCFrameworkForSample") {
    dependsOn(
        ":craftd-compose:linkReleaseFrameworkIosArm64",
        ":craftd-compose:linkReleaseFrameworkIosSimulatorArm64"
    )

    doLast {
        val composeBuildDir = project(":craftd-compose").layout.buildDirectory.get().asFile

        val deviceDir = File(composeBuildDir, "bin/iosArm64/releaseFramework")
        val simArmDir = File(composeBuildDir, "bin/iosSimulatorArm64/releaseFramework")

        fun pickSingleFramework(dir: File): File {
            require(dir.exists()) { "Diretório não encontrado: ${dir.absolutePath}" }
            return dir.listFiles { f -> f.isDirectory && f.name.endsWith(".framework") }
                ?.singleOrNull()
                ?: error("Nenhum .framework encontrado em ${dir.absolutePath}.")
        }

        val deviceFw = pickSingleFramework(deviceDir)
        val simFw = pickSingleFramework(simArmDir)

        val baseName = deviceFw.nameWithoutExtension
        val outDir = File(composeBuildDir, "XCFrameworks/release").apply { mkdirs() }
        val outXC = File(outDir, "$baseName.xcframework")

        if (outXC.exists()) {
            outXC.deleteRecursively()
            println("🧹 Removido build anterior: ${outXC.absolutePath}")
        }

        exec {
            commandLine(
                "xcodebuild", "-create-xcframework",
                "-framework", deviceFw.absolutePath,
                "-framework", simFw.absolutePath,
                "-output", outXC.absolutePath
            )
        }

        val sampleDir = File(rootDir, "samples/ios/Frameworks").apply { mkdirs() }
        val dstBundle = File(sampleDir, outXC.name)

        if (dstBundle.exists()) {
            dstBundle.deleteRecursively()
            println("🧹 Removido bundle anterior no sample: ${dstBundle.absolutePath}")
        }

        copy {
            from(outXC)
            into(dstBundle)
        }

        println("✅ Gerada: ${outXC.absolutePath}")
        println("✅ Copiada para: ${dstBundle.absolutePath}")
    }
}
