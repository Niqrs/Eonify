plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("auth.ui")
}

dependencies {
    implementation(project(":core:ui"))
}