plugins {
    id("evo-compose")
}

android.namespace = "com.evo.entrypoint"

dependencies {
    implementation(projects.presentation)
    implementation(projects.navigation.api)
    implementation(projects.resources)
}
