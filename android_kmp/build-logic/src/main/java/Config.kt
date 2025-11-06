import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {
    const val applicationId = "com.github.codandotv"
    const val compileSdkVersion = 35
    const val minSdkVersion = 21
    const val targetSdkVersion = 35
    const val versionName = "1.0"
    const val versionCode = 1
    val jvmTargetVersion = JavaVersion.VERSION_21
    val jvmTargetValue = JvmTarget.JVM_21
    val jvmTargetTool = Regex("\\d+").find(jvmTargetValue.toString())!!.value.toInt()
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val appName = "Craftd"
}