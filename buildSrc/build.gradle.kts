plugins {
    `kotlin-dsl`
}

repositories { //TODO: PluginManagement?
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    implementation(Dependencies.Kotlin.gradlePlugin)
    implementation(Dependencies.Android.gradlePlugin)
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}