plugins {
    id("evo-application")
}

val projectPath = File(rootDir.absolutePath)

val MAJOR_CODE = project.findProperty("MAJOR_CODE").toString().toInt()
val MINOR_CODE = project.findProperty("MINOR_CODE").toString().toInt()
val PATCH_CODE = project.findProperty("PATCH_CODE").toString().toInt()

android {
    defaultConfig {
        versionCode = MAJOR_CODE * 10000 + MINOR_CODE * 100 + PATCH_CODE
        versionName = "$MAJOR_CODE.$MINOR_CODE.$PATCH_CODE"
    }
}

dependencies {
    ProjectModule.values().forEach { module ->
        val currentModulePath = projectPath.resolve(module.moduleName).path
        implementAllModules(currentModulePath)
    }
}

enum class ProjectModule(val moduleName: String) {
    EntryPoint("entrypoint"),
    Data("data"),
    Feature("feature"),
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
