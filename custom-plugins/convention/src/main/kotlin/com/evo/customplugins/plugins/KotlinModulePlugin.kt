package com.evo.customplugins.plugins

import com.evo.customplugins.Config
import com.evo.customplugins.extensions.implementation
import com.evo.customplugins.extensions.libs
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

abstract class KotlinModulePlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("kotlin")
        }

        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = Config.JavaVersion
            targetCompatibility = Config.JavaVersion
        }

        dependencies {
            implementation(libs.koin.core)
            configureAdditionalDependencies(libs)
        }
    }

    open fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {}
}

class KotlinModulePluginImpl : KotlinModulePlugin() {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        implementation(libs.kotlin.coroutines)
    }
}