plugins {
    id("evo-compose")
}

android.namespace = "com.evo.settings"

dependencies {
    implementation(projects.navigation.api)
}