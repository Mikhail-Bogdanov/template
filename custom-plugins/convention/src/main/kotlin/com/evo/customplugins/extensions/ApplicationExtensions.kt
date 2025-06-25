package com.evo.customplugins.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.evo.customplugins.Config

fun ApplicationExtension.configureApplicationExtension() {
    namespace = Config.NAMESPACE_WITH_POINT + "app"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        applicationId = Config.NAMESPACE + ".template"
    }
    buildFeatures.buildConfig = true
    compileOptions {
        compileOptions {
            sourceCompatibility = Config.JAVA_VERSION
            targetCompatibility = Config.JAVA_VERSION
        }
    }
    buildTypes {
        release {
//            isMinifyEnabled = true
//            isShrinkResources = true

            signingConfig = signingConfigs.getByName("debug")

            proguardFiles("proguard-rules.pro")
        }
    }
}
