package com.evo.customplugins.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.evo.customplugins.extensions.*
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*

class AndroidFeature : ComposeModulePlugin() {

    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {
        moduleImplementation(":presentation")
        moduleImplementation(":resources")
        moduleImplementation(":data:storage:api")
        moduleImplementation(":data:database:api")
        moduleImplementation(":data:network:api")
        moduleImplementation(":screen")
        implementation(libs.ktor.client.core)
    }
}

class Application : BaseModule() {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.android.application.get().pluginId)
        pluginManager.apply(libs.plugins.kotlin.android.get().pluginId)
        pluginManager.apply(libs.plugins.ksp.get().pluginId)

        extensions.configure<ApplicationExtension> {
            configureApplicationExtension()
        }

        dependencies {
            implementation(libs.bundles.koin)
        }
        super.apply(target)
    }
}
