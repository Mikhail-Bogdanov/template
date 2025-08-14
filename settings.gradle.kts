rootProject.name = "template"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("custom-plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

buildCache {
    local {
        removeUnusedEntriesAfterDays = 20
    }
}

enum class ProjectModule(val moduleName: String) {
    App("app"),
    EntryPoint("entrypoint"),
    Data("data"),
    Domain("domain"),
    Feature("feature"),
    Presentation("presentation"),
    Navigation("navigation"),
    Resources("resources"),
    Coroutine("coroutine"),
    Di("di"),
    ;
}

val projectPath = File(rootDir.absolutePath)

ProjectModule.values().forEach { module ->
    val currentModulePath = projectPath.resolve(module.moduleName).path
    iterateUntilRoot(currentModulePath)
}

fun iterateUntilRoot(path: String) {
    val children = File(path).listFiles()?.toList() ?: return
    val isModule = children.any { it.name == "build.gradle.kts" }
    if (isModule) {
        val modulePath = path.drop(projectPath.path.length)
        val moduleName = modulePath.replace(File.separator, ":")
        include(moduleName)
    } else {
        for (child in children) {
            iterateUntilRoot(child.path)
        }
    }
}
