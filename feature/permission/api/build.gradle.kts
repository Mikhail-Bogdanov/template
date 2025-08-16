plugins {
    id("evo-compose")
}

android.namespace = "com.evo.permission.api"

dependencies {
    implementation(projects.navigation.api)
}
