plugins {
    id("evo-compose")
}

android.namespace = "com.evo.entrypoint"

dependencies {
    implementation(projects.presentation)
    implementation(projects.coroutine)
    implementation(projects.navigation.api)
    implementation(projects.feature.bottomBar.api)
    implementation(projects.feature.topBar.api)
}
