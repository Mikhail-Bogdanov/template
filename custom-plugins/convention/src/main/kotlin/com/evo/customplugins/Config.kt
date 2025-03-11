package com.evo.customplugins

import org.gradle.api.JavaVersion.VERSION_21

internal object Config {

    const val Namespace = "com.evo"
    const val NamespaceWithPoint = "$Namespace."
    const val CompileSdk = 34
    const val MinSdk = 28
    const val TargetSdk = 34

    private const val MajorCode = 1
    private const val MinorCode = 0
    private const val PatchCode = 0

    const val VersionCode = MajorCode * 10000 + MinorCode * 100 + PatchCode
    const val VersionName = "$MajorCode.$MinorCode.$PatchCode"

    val JavaVersion = VERSION_21

    val TestRunner = "androidx.test.runner.AndroidJUnitRunner"
}
