plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("profile.domain")
}

dependencies {
    api(project(":core:utils"))
}