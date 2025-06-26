plugins {
    id("evo-application")
}

dependencies {
    implementation(projects.entrypoint)
    ProjectModule.values().forEach { module ->
        val currentModulePath = rootDir.resolve(module.moduleName).path
        implementAllModules(currentModulePath)
    }
}

enum class ProjectModule(val moduleName: String) {
    Data("data"),
    Feature("feature"),
    ;
}

fun DependencyHandlerScope.implementAllModules(path: String) {
    val children = File(path).listFiles()?.toList() ?: return
    val isModule = children.any { it.name == "build.gradle.kts" }
    if (isModule) {
        val modulePath = path.drop(rootDir.path.length)
        val moduleName = modulePath.replace(File.separator, ":")
        implementation(project(moduleName))
    } else {
        for (child in children) {
            implementAllModules(child.path)
        }
    }
}
