plugins {
    id("evo-compose")
}

android.namespace = "com.evo.locale.api"

dependencies {
    implementation(projects.navigation.api)
}
