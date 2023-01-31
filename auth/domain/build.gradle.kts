plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("auth.domain")
}

dependencies {
    implementation(platform(Dependencies.Firebase.bom))
    api(Dependencies.Firebase.auth)
    api(Dependencies.PlayServices.auth)
}