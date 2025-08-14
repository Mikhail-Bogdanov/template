plugins {
    id("evo-compose")
}

android.namespace = "com.evo.topbar"

dependencies {
    implementation(projects.navigation.api)
}