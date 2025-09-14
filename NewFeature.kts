import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.pathString

println("Choose feature to create:")
println("1: UI Feature")
println("2: Data Feature")

val featureType = readln().toIntOrNull() ?: throw IllegalArgumentException("Invalid input")

val isTab = if (featureType == 1) {
    println("Is screen a tab?")
    println("1: Yes")
    println("2: No")
    readln().toIntOrNull() == 1
} else {
    false
}


val hasArgs = if (featureType == 1 && isTab.not()) {
    println("Does screen have arguments?")
    println("1: Yes")
    println("2: No")
    readln().toIntOrNull() == 1
} else {
    false
}


if (featureType !in 1..2) throw IllegalArgumentException("Invalid option")

println("Enter module name (only a..z and - allowed)")

val moduleName = readln().validateModuleName()

println("Creating feature...")

val screenName = moduleName.toScreenName()
val modulePath = KPath("feature", moduleName).pathString
val namespace = screenName.lowercase()
val screenModule = "${screenName}Module"
val screenArgs = "${screenName}Args".takeIf { hasArgs }

val moduleImplDir = KPath(modulePath, "impl").mkdirs()
val implPath = createFullModulePath(moduleImplDir, namespace)




when (featureType) {
    1 -> {
        createApiModule()
        createImplScreen()
        createDiModule(addScreen = true)
    }

    2 -> {
        createApiModule(isUi = false)
        createDiModule(addScreen = false)
    }
}

updateDiModule()

println("Feature created!")

////////////////////////////// CREATORS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

fun createApiModule(isUi: Boolean = true) {
    val moduleApiDir = KPath(modulePath, "api").mkdirs()
    val apiPath = createFullModulePath(moduleApiDir, namespace)
    makeFile(moduleApiDir, "build.gradle.kts") {
        """
            plugins {
                id("evo-${if (isUi) "compose" else "api"}")
            }
            ${if (isUi) "android.namespace = \"com.evo.$namespace\"" else ""}
            
            dependencies {
                ${if (isUi) "implementation(projects.navigation.api)" else ""}
            }
        """.trimIndent()
    }
    makeFile(apiPath, "$screenName.kt") {
        if (isUi) {
            """
                package com.evo.$namespace
                
                import com.evo.navigation.*

                abstract class $screenName : ${
                if (isTab) {
                    "BaseTab"
                } else {
                    "BaseScreen"
                }
            }()${
                screenArgs?.let {
                    ", ArgsOwner<$it>"
                }.orEmpty()
            }
                
                ${
                screenArgs?.let {
                    """
                data class $it(
                    val sampleData: Int,
                )
                        """
                }.orEmpty()
            }
            """.trimIndent()
        } else {
            """
                package com.evo.$namespace
            """.trimIndent()
        }
    }
    println("Api module created!")
}

fun updateDiModule() {
    val diModulePath = File("di${File.separator}impl").toPath()
    val diFullModulePath = createFullModulePath(diModulePath, "di")
    val diFile = KPath(diFullModulePath.pathString, "ModulesInitializer.kt").createFile()
    val modulesDelimiter = "/* [MODULES] */"
    val importsDelimiter = "/* [IMPORT] */"

    val importText = "import com.evo.${namespace}.$screenModule"
    val moduleText = "\t\t\t$screenModule(),"

    val diNewText = diFile.readText().replace(modulesDelimiter, "$modulesDelimiter\n$moduleText")
        .replace(importsDelimiter, "$importsDelimiter\n$importText")

    diFile.writeText(diNewText)
    println("DI module added!")
}

fun createDiModule(addScreen: Boolean) {
    makeFile(moduleImplDir, "build.gradle.kts") {
        """
            plugins {
                id("feature")
            }
            
            android.namespace = "com.evo.$namespace"
            
            dependencies {
                implementation(projects.feature.${screenName.lowercaseFirst()}.api)
            }
        """.trimIndent()
    }
    val screenText = "${
        if (isTab) {
            "singleOf"
        } else {
            "factoryOf"
        }
    }(::${screenName}Impl) bind $screenName::class".takeIf { addScreen }.orEmpty()

    makeFile(implPath, "$screenModule.kt") {
        """
            package com.evo.$namespace
    
            import com.evo.di.EvoModule
            import org.koin.core.module.Module
            import org.koin.core.module.dsl.${if (isTab) "singleOf" else "factoryOf"}
            import org.koin.dsl.bind

            class $screenModule : EvoModule() {
            
                override fun Module.initialize() {
                    $screenText
                    ${if (addScreen) "factoryOf(::ScreenModel)" else ""}
                }
            }

        """.trimIndent()
    }
}

private fun createImplScreen() {
    makeFile(implPath, "${screenName}Impl.kt") {
        """
            package com.evo.$namespace

            import androidx.compose.runtime.Composable
            import com.evo.presentation.ui.designsystem.atoms.*
            import com.evo.presentation.ui.designsystem.theme.*
            import org.koin.core.component.inject
            import org.koin.core.parameter.parametersOf

            internal class ${screenName}Impl(
                ${screenArgs?.let { "override val args: $it" }.orEmpty()}
             ) : $screenName() {
            
                override val screenModel: ScreenModel by inject {
                    parametersOf(${screenArgs?.let { "args" }.orEmpty()})
                }

                @Composable
                override fun Content() {
                    DesignSystem.ScreenScaffold(
                        
                    ) {
                        
                    }
                }
            }
        """.trimIndent()
    }
    makeFile(implPath, "ScreenModel.kt") {
        """
            package com.evo.$namespace

            import com.evo.navigation.BaseScreenModel

            class ScreenModel(
                ${screenArgs?.let { "args: $it," }.orEmpty()}
            ): BaseScreenModel<State>() {
            
                override val state = State(${screenArgs?.let { "args" }.orEmpty()})
            
            }
        """.trimIndent()
    }
    makeFile(implPath, "State.kt") {
        """
            package com.evo.$namespace

            class State(
                ${screenArgs?.let { "args: $it," }.orEmpty()}
            ) {
            
            }
        """.trimIndent()
    }
    println("Screen created!")
}

////////////////////////////// UTILS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

fun String.lowercaseFirst() = replaceFirstChar { it.lowercase() }
fun String.uppercaseFirst() = replaceFirstChar { it.uppercase() }
fun String.toScreenName(): String {
    val tempModuleName = toMutableList()

    while (tempModuleName.contains('-')) {
        val hyphenIndex = tempModuleName.indexOf('-')
        val hyphenNextSymbol = tempModuleName[hyphenIndex + 1]
        tempModuleName[hyphenIndex + 1] = hyphenNextSymbol.uppercaseChar()
        tempModuleName.removeAt(hyphenIndex)
    }

    return tempModuleName.joinToString("").uppercaseFirst()
}

fun Path.createFile() = toFile().apply { createNewFile() }
fun Path.mkdirs() = apply { createDirectories() }

fun createFullModulePath(modulePath: Path, namespace: String, vararg extraDir: String) = KPath(
    modulePath.pathString, "src", "main", "kotlin", "com", "evo", namespace, *extraDir,
).mkdirs()

fun makeFile(
    path: Path,
    name: String,
    vararg extraDir: String,
    content: () -> String,
) = KPath(path.pathString, *extraDir, name).createFile().writeText(content().trimIndent())

fun String.validateModuleName(): String {
    val isModuleNameValid = matches(Regex("^[a-z]+(-[a-z]+)*\$"))
    require(isModuleNameValid) {
        "Invalid module name!\nAllowed symbols: [a-z] OR [-]"
    }
    return this
}

fun KPath(path: String, vararg subpaths: String) = Paths.get(path, *subpaths)
