package com.evo.customplugins.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.evo.customplugins.extensions.configureApplicationExtension
import com.evo.customplugins.extensions.configureOrbitDependencies
import com.evo.customplugins.extensions.implementation
import com.evo.customplugins.extensions.libs
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeature : ComposeModulePlugin() {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":presentation-common:presentation-core")
        moduleImplementation(":data:user-data:api")
        moduleImplementation(":data:network:api")

        configureOrbitDependencies(libs)
    }
}

class Application : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidApplication.get().pluginId)
            apply(libs.plugins.kotlin.get().pluginId)
            apply(libs.plugins.ksp.get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            configureApplicationExtension()
        }

        dependencies {
            implementation(libs.bundles.koin)
        }
    }
}

class AppEntrypoint : ComposeModulePlugin("appEntrypoint") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":presentation-common:presentation-core")
        moduleImplementation(":data:theme:api")

        configureOrbitDependencies(libs)

        // ALL FEATURES HERE

    }
}