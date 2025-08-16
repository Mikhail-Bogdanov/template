package com.evo.customplugins.extensions

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.Config

internal fun LibraryExtension.configureAndroidFeatureModule() {
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
    }

    compileOptions {
        compileOptions {
            sourceCompatibility = Config.JAVA_VERSION
            targetCompatibility = Config.JAVA_VERSION
        }
    }

    buildTypes {
        release {
//            isMinifyEnabled = true

            signingConfig = signingConfigs.getByName("debug")

            proguardFiles("proguard-rules.pro")
        }
    }
}
