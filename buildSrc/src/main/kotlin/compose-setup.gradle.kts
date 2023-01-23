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
//        implementation(Dependencies.Android.Compose.tooling)
//        implementation(Dependencies.Android.Compose.icons)
//        implementation(compose.foundation)
}