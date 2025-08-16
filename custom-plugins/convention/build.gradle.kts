plugins {
    `kotlin-dsl`
}

group = "com.evo.customplugins"

dependencies {
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.android.gradle)
    compileOnly(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        // REGULAR
        register("evo-api") {
            id = "evo-api"
            implementationClass = "com.evo.customplugins.plugins.ApiModule"
        }
        register("evo-android") {
            id = "evo-android"
            implementationClass = "com.evo.customplugins.plugins.AndroidModule"
        }
        register("evo-compose") {
            id = "evo-compose"
            implementationClass = "com.evo.customplugins.plugins.ComposeModuleImpl"
        }
        register("evo-presentation") {
            id = "evo-presentation"
            implementationClass = "com.evo.customplugins.plugins.PresentationModule"
        }
        register("evo-base") {
            id = "evo-base"
            implementationClass = "com.evo.customplugins.plugins.BaseModule"
        }

        // ANDROID
        register("evo-application") {
            id = "evo-application"
            implementationClass = "com.evo.customplugins.plugins.Application"
        }
        register("feature") {
            id = "feature"
            implementationClass = "com.evo.customplugins.plugins.AndroidFeature"
        }
    }
}
