plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.diffplug.spotless") version "6.8.0"
    kotlin("plugin.serialization") version "1.6.21"
    id("kotlin-parcelize")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "io.simsim.iptv"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
    implementation("com.google.android.exoplayer:extension-rtmp:2.18.0")
    implementation("com.google.android.exoplayer:exoplayer-hls:2.18.0")
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

    // serialize
    implementation(KotlinX.serialization.core)

    // test
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.espresso.core)
    androidTestImplementation(AndroidX.test.ext.junit)
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint().editorConfigOverride(mapOf("disabled_rules" to "no-wildcard-imports"))
    }
}