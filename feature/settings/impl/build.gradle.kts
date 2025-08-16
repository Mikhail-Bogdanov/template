plugins {
    id("feature")
}

android.namespace = "com.evo.settings.impl"

dependencies {
    implementation(projects.feature.settings.api)
}
