object Dependencies {
    object Kotlin {
        const val ver = "1.7.21"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$ver"
    }

    object Android {
        private const val gradlePluginVersion = "8.1.0-alpha03"
        const val gradlePlugin = "com.android.tools.build:gradle:$gradlePluginVersion"

        private const val activityComposeVersion = "1.6.1"
        private const val coreKtxVersion = "1.9.0"
        private const val preferenceVersion = "1.2.0"

        const val activityCompose = "androidx.activity:activity-compose:$activityComposeVersion"
        const val coreKtx = "androidx.core:core-ktx:$coreKtxVersion"
        const val preference = "androidx.preference:preference-ktx:$preferenceVersion"
    }

    object Hilt {
        private const val version = "2.44.2"

        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"

        const val android = "com.google.dagger:hilt-android:$version"
        const val androidCompiler = "com.google.dagger:hilt-android-compiler:$version" //kapt implementation
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
    }

    object Compose {
        private const val version = "1.4.0-alpha04"
        private const val material3Version = "1.1.0-alpha05"
        private const val navigationVersion = "2.6.0-alpha04"

        const val animation = "androidx.compose.animation:animation:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val material3 = "androidx.compose.material3:material3:$material3Version"
        const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val icons = "androidx.compose.material:material-icons-core:$version"
    }

    object Accompanist {
        private const val version = "0.29.1-alpha"

        const val pager = "com.google.accompanist:accompanist-pager:$version"
        const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"
        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$version"
    }

    object Firebase {
        private const val bomVersion = "31.2.0"

        const val bom = "com.google.firebase:firebase-bom:$bomVersion"
        const val auth = "com.google.firebase:firebase-auth-ktx"
        const val firestore = "com.google.firebase:firebase-firestore-ktx"
        const val storage = "com.google.firebase:firebase-storage-ktx"
        const val messaging = "com.google.firebase:firebase-messaging-ktx"
        const val analytics = "com.google.firebase:firebase-analytics-ktx"
    }

    object PlayServices {
        private const val gradlePluginVersion = "4.3.15"
        const val gradlePlugin = "com.google.gms:google-services:$gradlePluginVersion"

        private const val authVersion = "20.4.1"

        const val auth = "com.google.android.gms:play-services-auth:$authVersion"
    }

    object Testing {
        const val junit4 = "junit:junit:4.13.2"
        const val junitAndroidExt = "androidx.test.ext:junit:1.1.5"
    }

    object Other {
        private const val coilVersion = "2.2.2"
        private const val arrowCoreVersion = "1.1.2"

        const val coil = "io.coil-kt:coil-compose:$coilVersion"
        const val arrowCore = "io.arrow-kt:arrow-core:$arrowCoreVersion"
    }
}