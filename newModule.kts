import java.io.File
import java.nio.file.Path
import kotlin.io.path.absolutePathString
import kotlin.io.path.createDirectories
import kotlin.io.path.pathString
import kotlin.io.path.Path as KPath

val MODULE_NAME = "settings-screen" // exaple: main-page

//////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

val SCREEN_NAME = MODULE_NAME.toScreenName()
val module = KPath(File("feature").absolutePath, MODULE_NAME)
val namespace = SCREEN_NAME.lowercase()
val screenNameImpl = "${SCREEN_NAME}Impl"

val moduleApiDir = KPath(module.absolutePath, "api").mkdirs()
val apiPath = createFullModulePath(moduleApiDir, namespace, KPath("ui"))
makeFile(moduleApiDir, "build.gradle.kts") {
    """
        plugins {
            id("evo-compose")
        }

        android.namespace = "com.evo.$namespace.api"
    """
}
makeFile(apiPath, "$SCREEN_NAME.kt") {
    """
        package com.evo.$namespace.ui
    
        import androidx.compose.runtime.Composable
        import com.evo.navigation.Screen
        import org.koin.compose.koinInject
    
        interface $SCREEN_NAME : Screen
    
        @Composable
        fun ${SCREEN_NAME.lowercaseFirst()}() = koinInject<$SCREEN_NAME>()
    """
}

val moduleImplDir = KPath(module.absolutePath, "impl").mkdirs()
val implPath = createFullModulePath(moduleImplDir, namespace)
makeFile(moduleImplDir, "build.gradle.kts") {
    """
        plugins {
            id("feature")
        }
        
        android.namespace = "com.evo.$namespace.impl"
        
        dependencies {
            implementation(projects.feature.${SCREEN_NAME.lowercaseFirst()}.api)
        }
    """
}
makeFile(implPath, KPath("ui"), "$SCREEN_NAME.kt") {
    """
        package com.evo.$namespace.ui

        import androidx.compose.runtime.Composable
        import com.evo.navigation.EvoNavigationHandler
    
        internal class $screenNameImpl : $SCREEN_NAME {
    
            @Composable
            override fun Content(navigator: EvoNavigationHandler) {
    
            }
        }
    """
}
makeFile(implPath, KPath("di"), "${SCREEN_NAME}Module.kt") {
    """
        package com.evo.$namespace.di

        import com.evo.$namespace.ui.$SCREEN_NAME
        import com.evo.$namespace.ui.$screenNameImpl
        import org.koin.core.module.dsl.factoryOf
        import org.koin.dsl.bind
        import org.koin.dsl.module
        
        object ${SCREEN_NAME}Module {
            private val module = module {
                factoryOf(::$screenNameImpl) bind $SCREEN_NAME::class
            }
        
            operator fun invoke() = module
        }
    """
}

fun String.lowercaseFirst() = this.replaceFirstChar { it.lowercase() }
fun String.uppercaseFirst() = this.replaceFirstChar { it.uppercase() }
fun String.toScreenName(): String {
    var tempModuleName = toMutableList()

    val isValidName = all { letter ->
        letter in 'A'..'z' || letter == '-'
    } && startsWith('-').not() && endsWith('-').not()

    check(isValidName)

    while (tempModuleName.contains('-')) {
        val hyphenIndex = tempModuleName.indexOf('-')
        val hyphenNextSymbol = tempModuleName[hyphenIndex + 1]
        tempModuleName.set(hyphenIndex + 1, hyphenNextSymbol.uppercaseChar())
        tempModuleName.removeAt(hyphenIndex)
    }

    return tempModuleName.joinToString("").uppercaseFirst()
}

fun Path.createFile() = this.toFile().apply { createNewFile() }
fun Path.mkdirs() = this.apply { createDirectories() }
val Path.absolutePath get() = this.absolutePathString()

fun createFullModulePath(modulePath: Path, namespace: String, extraDirs: Path = KPath("")) = KPath(
    modulePath.absolutePath,
    "src",
    "main",
    "kotlin",
    "com",
    "evo",
    namespace,
    extraDirs.pathString,
).mkdirs()

fun makeFile(path: Path, name: String) = KPath(path.absolutePath, name).createFile()

fun makeFile(
    path: Path,
    extraDirs: Path = KPath(""),
    name: String,
) = makeFile(KPath(path.absolutePath, extraDirs.pathString).mkdirs(), name)

fun makeFile(
    path: Path,
    name: String,
    content: () -> String
) = this.makeFile(path, name).writeText(content().trimIndent())

fun makeFile(
    path: Path,
    extraDirs: Path = KPath(""),
    name: String,
    content: () -> String
) = this.makeFile(path, extraDirs, name).writeText(content().trimIndent())