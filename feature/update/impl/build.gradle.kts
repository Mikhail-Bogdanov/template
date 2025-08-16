plugins {
    id("feature")
}

android.namespace = "com.evo.update"

dependencies {
    implementation(projects.feature.update.api)
}