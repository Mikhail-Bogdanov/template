plugins {
    id("evo-compose")
}

android.namespace = "com.evo.entrypoint"

dependencies {
    implementation(projects.presentation)
    implementation(projects.screen)
    implementation(projects.resources)
}
