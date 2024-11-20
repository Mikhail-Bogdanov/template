enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    includeBuild("custom-plugins")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

buildCache {
    local {
        removeUnusedEntriesAfterDays = 20
    }
}

rootProject.name = "template"

include(":app")

include(":app-entrypoint")

include(":resources")

include(":data:database:api")
include(":data:database:impl")

include(":data:datastore:api")
include(":data:datastore:impl")

include(":data:theme:impl")
include(":data:theme:api")

include(":data:user-data:impl")
include(":data:user-data:api")

include(":data:network:impl")
include(":data:network:api")

include(":data:common:data-core")
include(":data:common:data-extensions")
include(":data:common:data-utils")

include(":presentation-common:presentation-core")
include(":presentation-common:presentation-extensions")
include(":presentation-common:design-system")
include(":presentation-common:navigation:api")
include(":presentation-common:navigation:impl")