// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(Dependencies.Android.gradlePlugin)
    }
    repositories {
        mavenCentral()
    }
}
plugins {
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.kotlin.android").apply(false)
    id("org.jetbrains.kotlin.jvm").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}