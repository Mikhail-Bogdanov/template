plugins {
    id("feature")
}

android.namespace = "com.evo.locale"

dependencies {
    implementation(projects.feature.locale.api)
}