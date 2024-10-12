package com.evo.customplugins.plugins.android

import com.android.build.api.dsl.ApplicationExtension
import com.evo.customplugins.extensions.configureApplicationExtension
import com.evo.customplugins.extensions.implementation
import com.evo.customplugins.extensions.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class Application : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply(libs.plugins.androidApplication.get().pluginId)
            apply(libs.plugins.kotlin.get().pluginId)
            apply(libs.plugins.ksp.get().pluginId)
        }

        extensions.configure<ApplicationExtension> {
            configureApplicationExtension()
        }

        dependencies {
            implementation(libs.bundles.koin)
        }
    }
}