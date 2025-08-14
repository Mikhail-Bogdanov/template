plugins {
    id("feature")
}

android.namespace = "com.evo.crash"

dependencies {
    implementation(projects.feature.crash.api)
}