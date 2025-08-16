plugins {
    id("feature")
}

android.namespace = "com.evo.theme.impl"

dependencies {
    implementation(projects.feature.theme.api)
}
