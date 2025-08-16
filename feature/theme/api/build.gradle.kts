plugins {
    id("evo-compose")
}

android.namespace = "com.evo.theme.api"

dependencies {
    implementation(projects.navigation.api)
}
