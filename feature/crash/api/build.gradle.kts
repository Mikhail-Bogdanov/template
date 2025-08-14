plugins {
    id("evo-compose")
}

android.namespace = "com.evo.crash"

dependencies {
    implementation(projects.navigation.api)
}