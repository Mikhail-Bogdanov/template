package com.evo.customplugins.plugins.presentation

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureAndroidFeatureModule
import com.evo.customplugins.extensions.configureCompose
import com.evo.customplugins.extensions.configureComposeDependencies
import com.evo.customplugins.extensions.configureKoinDependencies
import com.evo.customplugins.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class PresentationUtils : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.composeCompiler.get().pluginId)
            apply(libs.plugins.jetbrainsCompose.get().pluginId)
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.kotlin.get().pluginId)
            apply(libs.plugins.ksp.get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidFeatureModule("presentationUtils")
            configureCompose(libs)
        }

        dependencies {
            configureComposeDependencies(libs)
            configureKoinDependencies(libs)
        }
    }
}