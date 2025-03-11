import java.io.File

/*===========================*/
val MODULE_NAME = "AAA"
val PACKAGE_NAME = "AAA"
val SCREEN_NAME = "AAA"
/*===========================*/

val sep = File.separator

val featurePath = File("feature").absolutePath
val newModule = File(featurePath.plus(sep).plus(MODULE_NAME))

val namespace = PACKAGE_NAME.lowercase()
val screenNameImpl = "${SCREEN_NAME}Impl"


val apiNewModule = File(newModule.absolutePath.plus(sep).plus("api"))
apiNewModule.mkdirs()

val apiPath = buildList {
    add(apiNewModule.absolutePath)
    add("src")
    add("main")
    add("kotlin")
    add("com")
    add("evo")
    add(namespace)
    add("ui")
}.joinToString(sep)

val newModuleApiDir = File(apiPath)
newModuleApiDir.mkdirs()

val newModuleApiScreenFile = File(
    newModuleApiDir.absolutePath.plus(sep).plus("$SCREEN_NAME.kt")
)
newModuleApiScreenFile.createNewFile()

val funName = SCREEN_NAME.replaceFirstChar { it.lowercase() }

newModuleApiScreenFile.writeText(
    """
        package com.evo.$namespace.ui

        import androidx.compose.runtime.Composable
        import com.evo.navigation.Screen
        import org.koin.compose.koinInject

        interface $SCREEN_NAME : Screen

        @Composable
        fun $funName() = koinInject<$SCREEN_NAME>()
    """.trimIndent()
)

val newModuleApiBuildGradleFile = File(
    apiNewModule.absolutePath.plus(sep).plus("build.gradle.kts")
).apply { createNewFile() }

newModuleApiBuildGradleFile.writeText(
    """
        plugins {
            id("evo-compose")
        }

        android.namespace = "com.evo.$namespace.api"
    """.trimIndent()
)

val implNewModule = File(newModule.absolutePath.plus(sep).plus("impl"))
implNewModule.mkdirs()

val implPath = buildList {
    add(implNewModule.absolutePath)
    add("src")
    add("main")
    add("kotlin")
    add("com")
    add("evo")
    add(namespace)
}.joinToString(sep)

val newModuleImplDir = File(implPath)
newModuleImplDir.mkdirs()

val newModuleImplScreenDir = File(implPath.plus(sep).plus("ui"))
newModuleImplScreenDir.mkdirs()

val newModuleImplScreenFile = File(
    newModuleImplScreenDir.absolutePath.plus(sep).plus("$SCREEN_NAME.kt")
)

newModuleImplScreenFile.createNewFile()

val newModuleImplDiDir = File(implPath.plus(sep).plus("di"))
newModuleImplDiDir.mkdirs()

val newModuleImplDiModuleFile = File(
    newModuleImplDiDir.absolutePath.plus(sep).plus("${SCREEN_NAME}Module.kt")
)

newModuleImplDiModuleFile.createNewFile()

newModuleImplScreenFile.writeText(
    """
        package com.evo.$namespace.ui

        import androidx.compose.runtime.Composable
        import com.evo.navigation.EvoNavigationHandler
        
        internal class $screenNameImpl : $SCREEN_NAME {
        
            @Composable
            override fun Content(navigator: EvoNavigationHandler) {
        
            }
        }
    """.trimIndent()
)

newModuleImplDiModuleFile.writeText(
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
    """.trimIndent()
)

val newModuleImplBuildGradleFile = File(
    implNewModule.absolutePath.plus(sep).plus("build.gradle.kts")
)

newModuleImplBuildGradleFile.createNewFile()

newModuleImplBuildGradleFile.writeText(
    """
        plugins {
            id("feature")
        }
        
        android.namespace = "com.evo.$namespace.impl"
        
        dependencies {
            implementation(projects.feature.$PACKAGE_NAME.api)
        }
    """.trimIndent()
)