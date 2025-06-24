import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.io.path.createDirectories
import kotlin.io.path.pathString

println("Choose feature to create:")
println("1: UI Feature")
println("2: Data Feature")

val featureType = readln().toIntOrNull() ?: throw IllegalArgumentException("Invalid input")

if (featureType !in 1..2) throw IllegalArgumentException("Invalid option")

println("Enter module name (only a..z and - allowed)")

val moduleName = readln().validateModuleName()

println("Creating feature...")

println("Initializing variables")

val screenName = moduleName.toScreenName()
val modulePath = KPath("feature", moduleName).pathString
val namespace = screenName.lowercase()
val screenModule = "${screenName}Module"

val moduleImplDir = KPath(modulePath, "impl").mkdirs()
val implPath = createFullModulePath(moduleImplDir, namespace)

createApiModule()


when (featureType) {
    1 -> {
        createImplScreen()
        createApiScreen()
        createDiModule(addScreen = true)
    }

    2 -> {
        createDiModule(addScreen = false)
    }
}

updateAppModule()

println("Feature created!")

////////////////////////////// CREATORS \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

fun createApiModule() {
    val moduleApiDir = KPath(modulePath, "api").mkdirs()
    val apiPath = createFullModulePath(moduleApiDir, namespace)
    makeFile(moduleApiDir, "build.gradle.kts") { "plugins {\n\tid(\"evo-kotlin\")\n}" }
    makeFile(apiPath, "SampleFile.kt") { "package com.evo.$namespace" }
    println("Api module created!")
}

fun updateAppModule() {
    val appModulePath = KPath(File("app").absolutePath)
    val appFullModulePath = createFullModulePath(appModulePath, "team")
    val appFile = KPath(appFullModulePath.pathString, "App.kt").createFile()
    val modulesDelimiter = "/* [MODULES] */"
    val importsDelimiter = "/* [IMPORT] */"

    val importText = "import com.evo.${namespace}.$screenModule"
    val moduleText = "\t\t$screenModule.module,"

    val appNewText = appFile.readText().replace(modulesDelimiter, "$modulesDelimiter\n$moduleText")
        .replace(importsDelimiter, "$importsDelimiter\n$importText")

    appFile.writeText(appNewText)
    println("DI module added to App module!")
}

fun createDiModule(addScreen: Boolean) {
    makeFile(moduleImplDir, "build.gradle.kts") {
        """
            plugins {
                id("feature")
            }
            
            android.namespace = "com.evo.$namespace.impl"
            
            dependencies {
                implementation(projects.feature.${screenName.lowercaseFirst()}.api)
            }
        """.trimIndent()
    }
    val screenText = """
            ${"\t\t"}factory(named(Screens.$screenName)) {
                ${"\t\t"}$screenName()
            ${"\t\t"}}
        """.takeIf { addScreen } ?: ""

    makeFile(implPath, "$screenModule.kt") {
        """
            package com.evo.$namespace
    
            ${if (addScreen) "import com.evo.$namespace.$screenName" else ""}
            import com.evo.screen.Screens
            import org.koin.core.qualifier.named
            import org.koin.dsl.module

            object InitialScreenModule {

                val module = module {
                    $screenText
                }
            }

        """.trimIndent()
    }
}

private fun createImplScreen() {
    makeFile(implPath, "$screenName.kt") {
        """
            package com.evo.$namespace

            import androidx.compose.runtime.Composable
            import com.evo.screen.NoArgs
            import com.evo.screen.Screen

            internal class $screenName : Screen<NoArgs, ScreenModel>(NoArgs, ScreenModel::class) {

                @Composable
                override fun Content() {
                    
                }
            }
        """.trimIndent()
    }
    makeFile(implPath, "ScreenModel.kt") {
        """
            package com.evo.$namespace

            import com.evo.screen.BaseScreenModel

            class ScreenModel : BaseScreenModel<State>() {
            
                override val state = State()
            
            }
        """.trimIndent()
    }
    makeFile(implPath, "State.kt") {
        """
            package com.evo.$namespace

            class State {
            
            }
        """.trimIndent()
    }
    println("Screen created!")
}

private fun createApiScreen() {
    val screenModulePath = KPath(File("screen").absolutePath)
    val screenFullModulePath = createFullModulePath(screenModulePath, "screen")
    val screensFile = KPath(screenFullModulePath.pathString, "Screens.kt").createFile()

    val screensDelimiter = "/* [SCREENS] */"

    val screensNewText = screensFile.readText().replace(
        oldValue = screensDelimiter,
        newValue = "$screensDelimiter\n\t$screenName,",
    )
    screensFile.writeText(screensNewText)

    println("Api for screen added to screens enum!")
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
