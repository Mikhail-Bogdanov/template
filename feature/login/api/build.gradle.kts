plugins {
    id("evo-compose")
}

android.namespace = "com.evo.login"

dependencies {
    implementation(projects.navigation.api)
}