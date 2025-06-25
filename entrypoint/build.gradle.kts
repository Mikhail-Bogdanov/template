plugins {
    id("evo-compose")
}

android.namespace = "com.evo.entrypoint"

//val projectPath = File(rootDir.absolutePath)

dependencies {
//    implementAllModules(projectPath.resolve("feature").path)
    implementation(projects.presentation)
    implementation(projects.data.storage.api)
}

//fun DependencyHandlerScope.implementAllModules(path: String) {
//    val children = File(path).listFiles()?.toList() ?: return
//    val isModule = children.any { it.name == "build.gradle.kts" }
//    if (isModule) {
//        val modulePath = path.drop(projectPath.path.length)
//        val moduleName = modulePath.replace(File.separator, ":")
//        implementation(project(moduleName))
//    } else {
//        for (child in children) {
//            implementAllModules(child.path)
//        }
//    }
//}
