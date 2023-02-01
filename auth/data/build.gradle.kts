plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("auth.data")
}

dependencies {
    implementation(project(":core:core"))

    implementation(project(":auth:domain"))

    implementation(Dependencies.Firebase.firestore)
}