plugins {
    id("feature")
}

android.namespace = "com.evo.window"

dependencies {
    implementation(projects.feature.window.api)
}