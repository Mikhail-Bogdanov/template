plugins {
    id("evo-compose")
}

android.namespace = "com.evo.permission"

dependencies {
    implementation(projects.navigation.api)
}