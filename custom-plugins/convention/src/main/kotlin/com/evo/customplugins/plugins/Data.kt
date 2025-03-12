package com.evo.customplugins.plugins

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureBuildConfig
import com.evo.customplugins.extensions.configureDatastoreDependencies
import com.evo.customplugins.extensions.configureNetworkDependencies
import com.evo.customplugins.extensions.configureRoomDependencies
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.DependencyHandlerScope

class Database : AndroidModulePlugin("database") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        configureRoomDependencies(libs)
    }

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }
}

class Storage : AndroidModulePlugin("storageImpl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:storage:api")
        configureDatastoreDependencies(libs)
    }

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }
}

class Network : AndroidModulePlugin("networkImpl") {

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }

    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:network:api")
        configureNetworkDependencies(libs)
    }
}