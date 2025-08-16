plugins {
    id("evo-compose")
}

android.namespace = "com.evo.crash.api"

dependencies {
    implementation(projects.navigation.api)
}
