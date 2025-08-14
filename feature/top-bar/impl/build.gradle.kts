plugins {
    id("feature")
}

android.namespace = "com.evo.topbar"

dependencies {
    implementation(projects.feature.topBar.api)
}