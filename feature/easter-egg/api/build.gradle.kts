plugins {
    id("evo-compose")
}

android.namespace = "com.evo.easteregg"

dependencies {
    implementation(projects.navigation.api)
}