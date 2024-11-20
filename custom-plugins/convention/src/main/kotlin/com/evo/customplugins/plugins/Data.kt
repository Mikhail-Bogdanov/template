package com.evo.customplugins.plugins

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureBuildConfig
import com.evo.customplugins.extensions.configureDatastoreDependencies
import com.evo.customplugins.extensions.configureNetworkDependencies
import com.evo.customplugins.extensions.configureRoomDependencies
import com.evo.customplugins.extensions.implementation
import com.evo.customplugins.extensions.moduleApi
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.DependencyHandlerScope

class Database : AndroidModulePlugin("database.impl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:common:data-core")
        moduleImplementation(":data:database:api")
        configureRoomDependencies(libs)
    }

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }
}

class Datastore : AndroidModulePlugin("datastore.impl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:common:data-core")
        moduleImplementation(":data:datastore:api")
        configureDatastoreDependencies(libs)
    }

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }
}

class DataUtils : AndroidModulePlugin("dataUtils")

class DataExtensions : AndroidModulePlugin("dataExtensions")

class DataCore : AndroidModulePlugin("dataCore") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleApi(":data:common:data-extensions")
        moduleApi(":data:common:data-utils")
    }
}

class Network : AndroidModulePlugin("network.impl") {

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }

    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:common:data-core")
        moduleImplementation(":data:network:api")
        moduleImplementation(":data:user-data:api")
        configureNetworkDependencies(libs)
    }
}

class Theme : AndroidModulePlugin("theme.impl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:datastore:api")
        moduleImplementation(":data:theme:api")
        implementation(libs.kotlin.coroutines)
    }
}

class UserData : AndroidModulePlugin("userData.impl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:datastore:api")
        moduleImplementation(":data:database:api")
        moduleImplementation(":data:user-data:api")
    }
}