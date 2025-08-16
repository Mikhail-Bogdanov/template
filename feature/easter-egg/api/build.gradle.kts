plugins {
    id("evo-compose")
}

android.namespace = "com.evo.easteregg.api"

dependencies {
    implementation(projects.navigation.api)
}
