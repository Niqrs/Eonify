import gradle.kotlin.dsl.accessors._02a2388d87905d5afb86ef5bd9da0463.kotlin

plugins {
    id("com.android.library")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.kotlinCompilerExtensionVersion
    }
}

kotlin {
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = ProjectConfig.jvmTarget
    }
}

dependencies {

    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.ui)
//        implementation(Dependencies.Android.Compose.tooling)
//        implementation(Dependencies.Android.Compose.icons)
//        implementation(compose.foundation)
}