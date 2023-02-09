plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("core.utils")
}

dependencies {
    api(Dependencies.Other.arrowCore)
}