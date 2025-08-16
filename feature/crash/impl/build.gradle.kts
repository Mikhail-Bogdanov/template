plugins {
    id("feature")
}

android.namespace = "com.evo.crash.impl"

dependencies {
    implementation(projects.feature.crash.api)
}
