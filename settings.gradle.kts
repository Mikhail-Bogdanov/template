enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("custom-plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

buildCache {
    local {
        removeUnusedEntriesAfterDays = 20
    }
}

rootProject.name = "template"

enum class ProjectModule(val moduleName: String) {
    App("app"),
    AppEntryPoint("app-entrypoint"),
    Data("data"),
    Feature("feature"),
    PresentationCommon("presentation-common"),
    Resources("resources"),
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