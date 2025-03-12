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
            implementationClass = "com.evo.customplugins.plugins.KotlinModulePluginImpl"
        }
        register("evo-compose") {
            id = "evo-compose"
            implementationClass = "com.evo.customplugins.plugins.ComposeModulePluginImpl"
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
        register("app-entrypoint") {
            id = "app-entrypoint"
            implementationClass = "com.evo.customplugins.plugins.AppEntrypoint"
        }

        // PRESENTATION
        register("design-system") {
            id = "design-system"
            implementationClass = "com.evo.customplugins.plugins.DesignSystem"
        }
        register("presentation-common") {
            id = "presentation-common"
            implementationClass = "com.evo.customplugins.plugins.PresentationCommon"
        }
        register("navigation") {
            id = "navigation"
            implementationClass = "com.evo.customplugins.plugins.Navigation"
        }
        register("navigation-api") {
            id = "navigation-api"
            implementationClass = "com.evo.customplugins.plugins.NavigationApi"
        }

        // DATA
        register("database") {
            id = "database"
            implementationClass = "com.evo.customplugins.plugins.Database"
        }
        register("storage") {
            id = "storage"
            implementationClass = "com.evo.customplugins.plugins.Storage"
        }
        register("network") {
            id = "network"
            implementationClass = "com.evo.customplugins.plugins.Network"
        }
    }
}