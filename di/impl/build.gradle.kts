plugins {
    id("evo-android")
}

android.namespace = "com.evo.di.impl"

dependencies {
    implementation(projects.entrypoint)
    implementation(projects.di.api)
    implementModules("data", "feature")
}

fun DependencyHandlerScope.implementModules(vararg modules: String) {
    modules.forEach {
        val currentModulePath = rootDir.resolve(it).path
        implementAllModules(currentModulePath)
    }
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
