plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("profile.data")
}

dependencies {
    implementation(project(":core:core"))

    implementation(project(":profile:domain"))

    implementation(platform(Dependencies.Firebase.bom)) //TODO: manage it
    implementation(Dependencies.Firebase.auth)
    implementation(Dependencies.Firebase.firestore)
    implementation(Dependencies.PlayServices.auth)
}