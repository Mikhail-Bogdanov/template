plugins {
    id("feature")
}

android.namespace = "com.evo.locale.impl"

dependencies {
    implementation(projects.feature.locale.api)
}
