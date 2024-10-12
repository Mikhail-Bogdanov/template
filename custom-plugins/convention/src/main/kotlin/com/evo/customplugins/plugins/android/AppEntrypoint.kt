package com.evo.customplugins.plugins.android

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureAndroidFeatureModule
import com.evo.customplugins.extensions.configureCompose
import com.evo.customplugins.extensions.configureComposeDependencies
import com.evo.customplugins.extensions.configureKoinDependencies
import com.evo.customplugins.extensions.configureOrbitDependencies
import com.evo.customplugins.extensions.libs
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AppEntrypoint : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.composeCompiler.get().pluginId)
            apply(libs.plugins.jetbrainsCompose.get().pluginId)
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.kotlin.get().pluginId)
            apply(libs.plugins.ksp.get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidFeatureModule("appEntrypoint")
            configureCompose(libs)
        }

        dependencies {
            moduleImplementation(":presentation-common:presentation-core")
            moduleImplementation(":data:theme:api")

            // ALL FEATURES HERE

            configureComposeDependencies(libs)
            configureKoinDependencies(libs)
            configureOrbitDependencies(libs)
        }
    }
}