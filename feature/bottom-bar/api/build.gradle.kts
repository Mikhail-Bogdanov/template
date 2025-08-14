plugins {
    id("evo-compose")
}

android.namespace = "com.evo.bottombar"

dependencies {
    implementation(projects.navigation.api)
}