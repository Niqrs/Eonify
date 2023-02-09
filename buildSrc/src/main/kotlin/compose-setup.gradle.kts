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

dependencies {

    implementation(Dependencies.Compose.navigation)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.runtime)
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.ui)

    implementation(Dependencies.Hilt.navigation)
}