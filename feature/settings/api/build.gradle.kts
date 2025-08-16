plugins {
    id("evo-compose")
}

android.namespace = "com.evo.settings.api"

dependencies {
    implementation(projects.navigation.api)
}
