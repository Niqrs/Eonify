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
include(":app")
include(":auth:ui")
include(":auth:data")
include(":auth:domain")
include(":profile:ui")

include(":core:core")
include(":core:ui")
include(":core:utils")