package com.evo.customplugins.plugins

import com.android.build.gradle.LibraryExtension
import com.evo.customplugins.Config
import com.evo.customplugins.extensions.*
import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

abstract class BaseModulePlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.kotlin.serialization.get().pluginId)
        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = Config.JAVA_VERSION
            targetCompatibility = Config.JAVA_VERSION
        }
        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(Config.JVM_TARGET)
                freeCompilerArgs.add("-Xcontext-receivers")
            }
        }
        dependencies {
            implementation(libs.koin.core)
            implementation(libs.kotlin.coroutines)
            implementation(libs.immutable.collections)
            implementation(libs.serialization)
            implementation(kotlin("reflect"))
            configureAdditionalDependencies(libs)
        }
    }

    open fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {}
}

open class AndroidModule : BaseModulePlugin() {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.android.library.get().pluginId)
        pluginManager.apply(libs.plugins.ksp.get().pluginId)
        pluginManager.apply(libs.plugins.kotlin.android.get().pluginId)

        extensions.configure<LibraryExtension> {
            configureAndroidFeatureModule()
        }

        dependencies {
            implementation(libs.bundles.android)
            implementation(libs.bundles.koin)
            configureAdditionalDependencies(libs)
            moduleImplementation(":di:api")
            moduleImplementation(":domain")
        }
        super.apply(target)
    }

    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {}
}

open class ComposeModule : AndroidModule() {

    override fun apply(target: Project) = with(target) {
        super.apply(target)
        pluginManager.apply(libs.plugins.kotlin.compose.compiler.get().pluginId)
        pluginManager.apply(libs.plugins.jetbrains.compose.get().pluginId)

        extensions.configure<LibraryExtension> {
            buildFeatures.compose = true
        }

        dependencies {
            implementation(libs.bundles.ui)
            implementation(platform(libs.compose.bom))
        }
    }
}

class ApiModule : BaseModule() {

    override fun apply(target: Project) = with(target) {
        super.apply(target)
        dependencies {
            moduleImplementation(":domain")
        }
    }
}

open class BaseModule : BaseModulePlugin() {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("java")
        pluginManager.apply(libs.plugins.kotlin.jvm.get().pluginId)
        super.apply(target)
    }
}
