plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("core.ui")
}

dependencies {
    implementation(Dependencies.Accompanist.systemUiController)
}
