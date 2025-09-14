plugins {
    id("feature")
}

android.namespace = "com.evo.bottombar"

dependencies {
    implementation(projects.feature.bottomBar.api)
    implementation(projects.feature.startScreen.api)
}
