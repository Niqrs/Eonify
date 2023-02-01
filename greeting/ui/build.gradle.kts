plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("splash.ui")
}

dependencies {
    implementation(project(":core:ui"))

    implementation(platform(Dependencies.Firebase.bom))
    api(Dependencies.Firebase.auth)

    implementation(Dependencies.Accompanist.pager)
    implementation(Dependencies.Accompanist.pagerIndicators)
}