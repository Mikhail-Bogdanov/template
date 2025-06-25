package com.evo.customplugins.extensions

import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.the

internal val Project.libs get() = the<LibrariesForLibs>()

internal fun DependencyHandlerScope.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandlerScope.ksp(dependencyNotation: Any) {
    add("ksp", dependencyNotation)
}

internal fun DependencyHandlerScope.moduleImplementation(moduleName: String) {
    add("implementation", project(moduleName))
}

internal fun DependencyHandlerScope.moduleApi(moduleName: String) {
    add("api", project(moduleName))
}
