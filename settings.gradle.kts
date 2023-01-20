pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Eonify"

// APP
include(":app")
// AUTH
include(":auth:ui")
include(":auth:data")
include(":auth:domain")
// PROFILE
include(":profile:ui")
// SPLASH
include(":splash:gg")
//CORE
include(":core:core")
include(":core:ui")
include(":core:utils")
include(":splash:ui")
