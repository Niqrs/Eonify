plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    namespace = ProjectConfig.namespace("eonify")
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = ProjectConfig.applicationId
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = ProjectConfig.javaVersion
        targetCompatibility = ProjectConfig.javaVersion
    }
    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }
    composeOptions {
        kotlinCompilerExtensionVersion = ProjectConfig.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:core"))

    implementation(project(":greeting:ui"))

    implementation(project(":auth:ui"))
    implementation(project(":auth:data"))
    implementation(project(":auth:domain"))

    implementation(project(":profile:ui"))
    implementation(project(":profile:data"))
    implementation(project(":profile:domain"))

    // Import the BoM for the Firebase platform
    implementation(platform(Dependencies.Firebase.bom)) //TODO: manage it
    implementation(Dependencies.Firebase.auth)
    implementation(Dependencies.Firebase.firestore)
    implementation(Dependencies.PlayServices.auth)


    implementation(Dependencies.Android.coreKtx)
    implementation(Dependencies.Android.activityCompose)
    implementation(Dependencies.Android.preference)

    implementation(Dependencies.Compose.ui)
    implementation(Dependencies.Compose.tooling)
    implementation(Dependencies.Compose.material3)
    implementation(Dependencies.Compose.navigation)

    implementation(Dependencies.Hilt.android)
    kapt(Dependencies.Hilt.androidCompiler)

    testImplementation(Dependencies.Testing.junit4)
    androidTestImplementation(Dependencies.Testing.junit4)
    androidTestImplementation(Dependencies.Testing.junitAndroidExt)
}

kapt {
    correctErrorTypes = true
}