plugins {
    id("evo-compose")
}

android.namespace = "com.evo.locale"

dependencies {
    implementation(projects.navigation.api)
}