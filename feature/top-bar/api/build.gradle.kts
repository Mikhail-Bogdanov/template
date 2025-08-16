plugins {
    id("evo-compose")
}

android.namespace = "com.evo.topbar.api"

dependencies {
    implementation(projects.navigation.api)
}
