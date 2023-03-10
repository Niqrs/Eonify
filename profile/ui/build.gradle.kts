plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("profile.ui")
}

dependencies {
    implementation(project(":core:ui"))

    implementation(project(":profile:domain"))

    implementation(Dependencies.Other.coil)
    api(Dependencies.Accompanist.systemUiController)
}