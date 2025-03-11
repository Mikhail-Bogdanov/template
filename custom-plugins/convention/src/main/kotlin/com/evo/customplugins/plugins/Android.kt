package com.evo.customplugins.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.evo.customplugins.extensions.configureApplicationExtension
import com.evo.customplugins.extensions.configureKoinDependencies
import com.evo.customplugins.extensions.configureOrbitDependencies
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
        moduleImplementation(":presentation-common:presentation-extensions")
        moduleImplementation(":presentation-common:navigation:api")
        moduleImplementation(":presentation-common:design-system")
        moduleImplementation(":data:user-data:api")
        moduleImplementation(":data:app-data:api")
        moduleImplementation(":data:network:api")
        moduleImplementation(":resources")

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
            configureKoinDependencies(libs)
        }
    }
}

class AppEntrypoint : ComposeModulePlugin("appEntrypoint") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":presentation-common:presentation-extensions")
        moduleImplementation(":presentation-common:navigation:api")
        moduleImplementation(":presentation-common:design-system")
        moduleImplementation(":data:app-data:api")

        configureOrbitDependencies(libs)
    }
}