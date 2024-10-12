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

internal fun DependencyHandlerScope.configureComposeDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.ui)
    implementation(platform(libs.compose.bom))
}

internal fun DependencyHandlerScope.configureKoinDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.koin)
}

internal fun DependencyHandlerScope.configureOrbitDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.orbit)
}

internal fun DependencyHandlerScope.configureRoomDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}

internal fun DependencyHandlerScope.configureDatastoreDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.dataStore)
}

internal fun DependencyHandlerScope.configurePagingDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.paging)
}

internal fun DependencyHandlerScope.configureNetworkDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.network)
}

internal fun DependencyHandlerScope.configureTestingDependencies(
    libs: LibrariesForLibs
) {
    implementation(libs.bundles.testing)
}