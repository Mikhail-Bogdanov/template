package com.evo.customplugins.plugins

import com.evo.customplugins.extensions.configureOrbitDependencies
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.DependencyHandlerScope

class Navigation : ComposeModulePlugin("navigation") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":presentation-common:navigation:api")
    }
}

class NavigationApi : ComposeModulePlugin("navigationApi")

class DesignSystem : ComposeModulePlugin("designSystem") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":presentation-common:presentation-extensions")
        moduleImplementation(":data:app-data:api")
    }
}

class PresentationExtensions : ComposeModulePlugin("presentationExtensions") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        configureOrbitDependencies(libs)
    }
}