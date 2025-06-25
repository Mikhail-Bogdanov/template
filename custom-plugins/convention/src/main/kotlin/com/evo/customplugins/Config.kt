package com.evo.customplugins

import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Config {

    const val NAMESPACE = "com.evo"
    const val NAMESPACE_WITH_POINT = "$NAMESPACE."
    const val COMPILE_SDK = 35
    const val MIN_SDK = 28
    const val TARGET_SDK = 35

    val JAVA_VERSION = JavaVersion.VERSION_21
    val JVM_TARGET = JvmTarget.JVM_21

    const val TEST_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
}
