package com.evo.customplugins.plugins

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureAndroidFeatureModule
import com.evo.customplugins.extensions.configureKoinDependencies
import com.evo.customplugins.extensions.libs
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

abstract class AndroidModulePlugin(private val moduleNamespace: String? = null) : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.kotlin.get().pluginId)
            apply(libs.plugins.ksp.get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidFeatureModule(moduleNamespace)
            configureAdditionalExtensions()
        }

        dependencies {
            configureKoinDependencies(libs)
            configureAdditionalDependencies(libs)
        }
    }

    open fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {}

    open fun LibraryExtension.configureAdditionalExtensions() {}
}

class AndroidModulePluginImpl : AndroidModulePlugin()