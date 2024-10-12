package com.evo.customplugins.extensions

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.Config
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.plugins.PluginManager

internal fun LibraryExtension.configureAndroidFeatureModule(
    moduleNamespace: String? = null
) {
    compileSdk = Config.CompileSdk
    namespace = if (moduleNamespace != null) {
        Config.NamespaceWithPoint + moduleNamespace
    } else {
        namespace
    }

    defaultConfig {
        minSdk = Config.MinSdk
    }
    buildTypes {
        release {
            isMinifyEnabled = true

            signingConfig = signingConfigs.getByName("debug")

            proguardFiles("proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = Config.JavaVersion
        targetCompatibility = Config.JavaVersion
    }

}

internal fun LibraryExtension.configureCompose(
    libs: LibrariesForLibs
) {
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = libs.versions.compiler.get()
}

internal fun LibraryExtension.configureBuildConfig() {
    buildFeatures.buildConfig = true
}

internal fun PluginManager.configureComposePlugins(
    libs: LibrariesForLibs
) {
    apply(libs.plugins.composeCompiler.get().pluginId)
    apply(libs.plugins.jetbrainsCompose.get().pluginId)
}

internal fun PluginManager.configureAndroidPlugins(
    libs: LibrariesForLibs
) {
    apply(libs.plugins.androidLibrary.get().pluginId)
    apply(libs.plugins.kotlin.get().pluginId)
    apply(libs.plugins.ksp.get().pluginId)
}