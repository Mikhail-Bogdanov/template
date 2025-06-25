plugins {
    id("feature")
}

android.namespace = "com.evo.initialscreen.impl"

dependencies {
    implementation(projects.feature.initialScreen.api)
}