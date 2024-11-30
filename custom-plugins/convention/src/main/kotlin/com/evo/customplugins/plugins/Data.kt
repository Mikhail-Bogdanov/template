package com.evo.customplugins.plugins

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.extensions.configureBuildConfig
import com.evo.customplugins.extensions.configureDatastoreDependencies
import com.evo.customplugins.extensions.configureNetworkDependencies
import com.evo.customplugins.extensions.configureRoomDependencies
import com.evo.customplugins.extensions.implementation
import com.evo.customplugins.extensions.moduleImplementation
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.kotlin.dsl.DependencyHandlerScope

class Database : AndroidModulePlugin("database.impl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:common:data-extensions")
        moduleImplementation(":data:common:data-utils")
        moduleImplementation(":data:database:api")
        configureRoomDependencies(libs)
    }

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }
}

class Datastore : AndroidModulePlugin("datastore.impl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:common:data-utils")
        moduleImplementation(":data:common:data-extensions")
        moduleImplementation(":data:datastore:api")
        configureDatastoreDependencies(libs)
    }

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }
}

class DataUtils : AndroidModulePlugin("dataUtils")

class DataExtensions : AndroidModulePlugin("dataExtensions")

class Network : AndroidModulePlugin("network.impl") {

    override fun LibraryExtension.configureAdditionalExtensions() {
        configureBuildConfig()
    }

    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:common:data-utils")
        moduleImplementation(":data:common:data-extensions")
        moduleImplementation(":data:network:api")
        moduleImplementation(":data:user-data:api")
        configureNetworkDependencies(libs)
    }
}

class AppData : AndroidModulePlugin("appData.impl") {
    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":data:datastore:api")
        moduleImplementation(":data:database:api")
        moduleImplementation(":data:app-data:api")
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