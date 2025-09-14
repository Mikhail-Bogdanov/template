plugins {
    id("feature")
}

android.namespace = "com.evo.startscreen"

dependencies {
    implementation(projects.feature.startScreen.api)
}