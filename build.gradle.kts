plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.compose) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.ksp) apply false
}

tasks.register<Copy>("installGitHooks") {
    group = "setup"
    description = "Copies git hook scripts into .git/hooks"

    val hooksSrc = file("scripts/hooks/bat")
    val hooksDest = file(".git/hooks")

    from(hooksSrc)
    into(hooksDest)

    doLast {
        println("Hooks Copied!")
        hooksDest.listFiles()?.forEach { file ->
            if (file.isFile) {
                file.setExecutable(true)
            }
        }
    }
}

gradle.projectsEvaluated {
    subprojects.forEach { proj ->
        proj.tasks.matching { it.name.contains("build", ignoreCase = true) }.configureEach {
            dependsOn(rootProject.tasks.named("installGitHooks"))
        }
    }
}
