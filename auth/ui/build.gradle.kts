plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("auth.ui")
}

dependencies {
    implementation(project(":core:ui"))

    // Import the BoM for the Firebase platform
    implementation(platform(Dependencies.Firebase.bom))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(Dependencies.Firebase.auth)

    // Also add the dependency for the Google Play services library and specify its version
    implementation(Dependencies.PlayServices.auth)
}