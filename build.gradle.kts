plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
}

copy {
    val hooksSrc = file("project/hooks/bat")
    val hooksDest = file(".git/hooks")

    from(hooksSrc)
    into(hooksDest)

    hooksDest.listFiles()?.forEach { file ->
        if (file.isFile) { file.setExecutable(true) }
    }

    println("Hooks Copied!")
}
