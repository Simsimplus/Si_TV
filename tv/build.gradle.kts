plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.diffplug.spotless") version "6.8.0"
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.simsim.iptv"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kapt {
        correctErrorTypes = true
    }
    viewBinding.isEnabled = true
}

dependencies {
    // hilt
    implementation("com.google.dagger:hilt-android:2.42")
    kapt("com.google.dagger:hilt-android-compiler:2.42")

    // androidx
    implementation(AndroidX.core.ktx)
    implementation(AndroidX.leanback)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.activity.ktx)
    implementation(AndroidX.fragment.ktx)

    // glide
    implementation("com.github.bumptech.glide:glide:4.13.2")

    // exo
    implementation("com.google.android.exoplayer:exoplayer:2.18.0")
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint().editorConfigOverride(mapOf("disabled_rules" to "no-wildcard-imports"))
    }
}