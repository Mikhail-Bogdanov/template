package com.evo.customplugins.plugins.data

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureAndroidFeatureModule
import com.evo.customplugins.extensions.configureBuildConfig
import com.evo.customplugins.extensions.configureDatastoreDependencies
import com.evo.customplugins.extensions.configureKoinDependencies
import com.evo.customplugins.extensions.libs
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class Datastore : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidLibrary.get().pluginId)
            apply(libs.plugins.kotlin.get().pluginId)
            apply(libs.plugins.ksp.get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureAndroidFeatureModule("datastore")
            configureBuildConfig()
        }

        dependencies {
            moduleImplementation(":data:common:data-core")
            moduleImplementation(":data:datastore:api")
            configureKoinDependencies(libs)
            configureDatastoreDependencies(libs)
        }
    }
}