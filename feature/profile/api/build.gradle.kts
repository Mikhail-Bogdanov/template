plugins {
    id("evo-compose")
}

android.namespace = "com.evo.profile"

dependencies {
    implementation(projects.navigation.api)
}