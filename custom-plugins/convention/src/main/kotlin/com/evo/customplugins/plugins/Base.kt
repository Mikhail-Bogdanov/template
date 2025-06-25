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

abstract class BaseModule(private val implementDomain: Boolean = true) : Plugin<Project> {

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
            if (implementDomain) {
                moduleImplementation(":domain")
            }
        }
    }

    open fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {}
}

abstract class AndroidModulePlugin(private val moduleNamespace: String? = null) : BaseModule() {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply(libs.plugins.android.library.get().pluginId)
        pluginManager.apply(libs.plugins.ksp.get().pluginId)
        pluginManager.apply(libs.plugins.kotlin.android.get().pluginId)

        extensions.configure<LibraryExtension> {
            configureAndroidFeatureModule(moduleNamespace)
        }

        dependencies {
            implementation(libs.bundles.android)
            implementation(libs.bundles.koin)
            configureAdditionalDependencies(libs)
        }
        super.apply(target)
    }

    override fun DependencyHandlerScope.configureAdditionalDependencies(libs: LibrariesForLibs) {}
}

abstract class ComposeModulePlugin(
    moduleNamespace: String? = null,
    private val implementScreen: Boolean = true,
) : AndroidModulePlugin(moduleNamespace) {

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
            if (implementScreen) {
                moduleImplementation(":screen")
            }
        }
    }
}

class CleanKotlinModule : BaseModule() {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("java")
        pluginManager.apply(libs.plugins.kotlin.jvm.get().pluginId)
        super.apply(target)
    }
}

class CleanComposeModule : ComposeModulePlugin()
class ScreenModule : ComposeModulePlugin(implementScreen = false)
class CleanAndroidModule : AndroidModulePlugin()

class Domain : BaseModule(false) {

    override fun apply(target: Project) = with(target) {
        pluginManager.apply("java")
        pluginManager.apply(libs.plugins.kotlin.jvm.get().pluginId)
        super.apply(target)
    }
}
