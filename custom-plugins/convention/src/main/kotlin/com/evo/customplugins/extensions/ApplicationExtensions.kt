package com.evo.customplugins.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.evo.customplugins.Config

fun ApplicationExtension.configureApplicationExtension() {
    namespace = Config.NamespaceWithPoint + "app"
    compileSdk = Config.CompileSdk

    defaultConfig {
        minSdk = Config.MinSdk
        targetSdk = Config.TargetSdk

        applicationId = Config.Namespace

        versionCode = Config.VersionCode
        versionName = Config.VersionName
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true

            signingConfig = signingConfigs.getByName("debug")

            proguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Config.JavaVersion
        targetCompatibility = Config.JavaVersion
    }
    androidResources {
        generateLocaleConfig = true
    }
}