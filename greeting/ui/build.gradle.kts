plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("splash.ui")
}

dependencies {
    implementation(project(":core:ui"))
}