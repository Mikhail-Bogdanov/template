package com.evo.customplugins.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.evo.customplugins.Config
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.pathString

internal fun ApplicationExtension.configureApplicationExtension() {
    namespace = Config.NAMESPACE_WITH_POINT + "app"
    compileSdk = Config.COMPILE_SDK

    defaultConfig {
        val major = Code.MAJOR.current
        val minor = Code.MINOR.current
        val patch = Code.PATCH.current

        versionCode = major * 10000 + minor * 100 + patch
        versionName = "$major.$minor.$patch"

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

internal enum class Code(val filePath: String) {
    MAJOR(Path("project", "version", "major.txt").pathString) {
        override val file = File(filePath)
    },
    MINOR(Path("project", "version", "minor.txt").pathString) {
        override val file = File(filePath)
    },
    PATCH(Path("project", "version", "patch.txt").pathString) {
        override val file = File(filePath)
    },
    ;

    abstract val file: File
    val current get() = file.readText().trim().toInt()
}
