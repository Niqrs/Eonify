import org.gradle.api.JavaVersion

object ProjectConfig {
    fun namespace(name: String) = "com.niqr.$name"

    const val compileSdk = 33
    const val applicationId = "com.niqr.eonify"
    const val minSdk = 27
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlinCompilerExtensionVersion = "1.4.0-alpha02"

    val javaVersion = JavaVersion.VERSION_11
    const val jvmTarget = "11"
}