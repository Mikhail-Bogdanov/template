package com.evo.customplugins.plugins

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureCompose
import com.evo.customplugins.extensions.configureComposeDependencies
import com.evo.customplugins.extensions.libs
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

abstract class ComposeModulePlugin(
    moduleNamespace: String? = null
) : AndroidModulePlugin(moduleNamespace) {
    override fun apply(target: Project) = with(target) {
        super.apply(target)
        with(pluginManager) {
            apply(libs.plugins.composeCompiler.get().pluginId)
            apply(libs.plugins.jetbrainsCompose.get().pluginId)
        }

        extensions.configure<LibraryExtension> {
            configureCompose(libs)
        }

        dependencies {
            configureComposeDependencies(libs)
        }
    }
}

class ComposeModulePluginImpl : ComposeModulePlugin() {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":presentation-common:presentation-extensions")
        moduleImplementation(":presentation-common:navigation:api")
        moduleImplementation(":presentation-common:design-system")
    }
}