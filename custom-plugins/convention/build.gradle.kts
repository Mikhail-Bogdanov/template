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
        register("evo-library") {
            id = "evo-library"
            implementationClass = "com.evo.customplugins.plugins.AndroidModulePluginImpl"
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
        register("presentation-core") {
            id = "presentation-core"
            implementationClass = "com.evo.customplugins.plugins.PresentationCore"
        }
        register("design-system") {
            id = "design-system"
            implementationClass = "com.evo.customplugins.plugins.DesignSystem"
        }
        register("presentation-extensions") {
            id = "presentation-extensions"
            implementationClass = "com.evo.customplugins.plugins.PresentationExtensions"
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
        register("data-core") {
            id = "data-core"
            implementationClass = "com.evo.customplugins.plugins.DataCore"
        }
        register("data-extensions") {
            id = "data-extensions"
            implementationClass = "com.evo.customplugins.plugins.DataExtensions"
        }
        register("data-utils") {
            id = "data-utils"
            implementationClass = "com.evo.customplugins.plugins.DataUtils"
        }
        register("local-storage") {
            id = "local-storage"
            implementationClass = "com.evo.customplugins.plugins.LocalStorage"
        }
        register("database") {
            id = "database"
            implementationClass = "com.evo.customplugins.plugins.Database"
        }
        register("datastore") {
            id = "datastore"
            implementationClass = "com.evo.customplugins.plugins.Datastore"
        }
        register("user-data") {
            id = "user-data"
            implementationClass = "com.evo.customplugins.plugins.UserData"
        }
        register("theme") {
            id = "theme"
            implementationClass = "com.evo.customplugins.plugins.Theme"
        }
        register("network") {
            id = "network"
            implementationClass = "com.evo.customplugins.plugins.Network"
        }
    }
}