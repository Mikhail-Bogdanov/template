plugins {
    id("feature")
}

android.namespace = "com.evo.theme"

dependencies {
    implementation(projects.feature.theme.api)
}