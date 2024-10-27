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
        // ANDROID
        register("evo-application") {
            id = "evo-application"
            implementationClass = "com.evo.customplugins.plugins.android.Application"
        }
        register("feature") {
            id = "feature"
            implementationClass = "com.evo.customplugins.plugins.android.AndroidFeature"
        }
        register("app-entrypoint") {
            id = "app-entrypoint"
            implementationClass = "com.evo.customplugins.plugins.android.AppEntrypoint"
        }
        register("evo-library") {
            id = "evo-library"
            implementationClass = "com.evo.customplugins.plugins.android.Library"
        }

        // PRESENTATION
        register("presentation-core") {
            id = "presentation-core"
            implementationClass = "com.evo.customplugins.plugins.presentation.PresentationCore"
        }
        register("design-system") {
            id = "design-system"
            implementationClass = "com.evo.customplugins.plugins.presentation.DesignSystem"
        }
        register("presentation-extensions") {
            id = "presentation-extensions"
            implementationClass = "com.evo.customplugins.plugins.presentation.PresentationExtensions"
        }
        register("navigation") {
            id = "navigation"
            implementationClass = "com.evo.customplugins.plugins.presentation.Navigation"
        }

        // DATA
        register("data-core") {
            id = "data-core"
            implementationClass = "com.evo.customplugins.plugins.data.DataCore"
        }
        register("data-extensions") {
            id = "data-extensions"
            implementationClass = "com.evo.customplugins.plugins.data.DataExtensions"
        }
        register("data-utils") {
            id = "data-utils"
            implementationClass = "com.evo.customplugins.plugins.data.DataUtils"
        }
        register("local-storage") {
            id = "local-storage"
            implementationClass = "com.evo.customplugins.plugins.data.LocalStorage"
        }
        register("database") {
            id = "database"
            implementationClass = "com.evo.customplugins.plugins.data.Database"
        }
        register("datastore") {
            id = "datastore"
            implementationClass = "com.evo.customplugins.plugins.data.Datastore"
        }
        register("user-data") {
            id = "user-data"
            implementationClass = "com.evo.customplugins.plugins.data.UserData"
        }
        register("theme") {
            id = "theme"
            implementationClass = "com.evo.customplugins.plugins.data.Theme"
        }
        register("network") {
            id = "network"
            implementationClass = "com.evo.customplugins.plugins.data.Network"
        }
    }
}