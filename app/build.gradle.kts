plugins {
    id("evo-application")
}

val projectPath = File(rootDir.absolutePath)

dependencies {
    ProjectModule.values().forEach { module ->
        val currentModulePath = projectPath.resolve(module.moduleName).path
        implementAllModules(currentModulePath)
    }
}

enum class ProjectModule(val moduleName: String) {
    AppEntryPoint("app-entrypoint"),
    Data("data"),
    Domain("domain"),
    Feature("feature"),
    Presentation("presentation"),
    Resources("resources"),
    ;
}

fun DependencyHandlerScope.implementAllModules(path: String) {
    val children = File(path).listFiles()?.toList() ?: return
    val isModule = children.any { it.name == "build.gradle.kts" }
    if (isModule) {
        val modulePath = path.drop(projectPath.path.length)
        val moduleName = modulePath.replace(File.separator, ":")
        implementation(project(moduleName))
    } else {
        for (child in children) {
            implementAllModules(child.path)
        }
    }
}