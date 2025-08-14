plugins {
    id("feature")
}

android.namespace = "com.evo.settings"

dependencies {
    implementation(projects.feature.settings.api)
}