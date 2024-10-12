package com.evo.customplugins.plugins.data

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureAndroidFeatureModule
import com.evo.customplugins.extensions.configureKoinDependencies
import com.evo.customplugins.extensions.implementation
import com.evo.customplugins.extensions.libs
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class Theme : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.kotlin.get().pluginId)
            apply(libs.plugins.ksp.get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidFeatureModule("theme")
        }

        dependencies {
            moduleImplementation(":data:local-storage:api")
            moduleImplementation(":data:theme:api")
            configureKoinDependencies(libs)
            implementation(libs.kotlin.coroutines)
        }
    }
}