package com.evo.customplugins.extensions

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.VariantDimension
import com.evo.customplugins.Config
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.pathString

internal fun ApplicationExtension.configureApplicationExtension() {
    namespace = Config.NAMESPACE_WITH_POINT + "app"
    compileSdk = Config.COMPILE_SDK

    val major = Code.MAJOR.current
    val minor = Code.MINOR.current
    val patch = Code.PATCH.current

    defaultConfig {
        val name = "$major.$minor.$patch"
        versionCode = major * 10000 + minor * 100 + patch
        versionName = name

        minSdk = Config.MIN_SDK
        targetSdk = Config.TARGET_SDK

        applicationId = Config.NAMESPACE + ".template"

        signingConfig = signingConfigs.getByName("debug")

        stringField("appVersion", name)
    }
    buildFeatures.buildConfig = true
    compileOptions {
        compileOptions {
            sourceCompatibility = Config.JAVA_VERSION
            targetCompatibility = Config.JAVA_VERSION
        }
    }
    buildTypes {
        debug {

        }
        release {
//            isMinifyEnabled = true
//            isShrinkResources = true

        }
    }
}

fun VariantDimension.stringField(name: String, value: String) {
    buildConfigField("String", name, value)
}

fun VariantDimension.booleanField(name: String, value: Boolean) {
    buildConfigField("boolean", name, value.toString())
}

internal enum class Code(filePath: String) {
    MAJOR(Path("project", "version", "major.txt").pathString),
    MINOR(Path("project", "version", "minor.txt").pathString),
    PATCH(Path("project", "version", "patch.txt").pathString),
    ;

    private val file = File(filePath)
    val current = file.readText().trim().toInt()
}
