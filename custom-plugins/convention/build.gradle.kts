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
        register("evo-kotlin") {
            id = "evo-kotlin"
            implementationClass = "com.evo.customplugins.plugins.CleanKotlinModule"
        }
        register("evo-android") {
            id = "evo-android"
            implementationClass = "com.evo.customplugins.plugins.CleanAndroidModule"
        }
        register("evo-compose") {
            id = "evo-compose"
            implementationClass = "com.evo.customplugins.plugins.CleanComposeModule"
        }
        register("domain") {
            id = "domain"
            implementationClass = "com.evo.customplugins.plugins.Domain"
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

        // PRESENTATION
        register("screen") {
            id = "screen"
            implementationClass = "com.evo.customplugins.plugins.ScreenModule"
        }
    }
}
