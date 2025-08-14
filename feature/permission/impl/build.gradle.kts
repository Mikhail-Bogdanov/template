plugins {
    id("feature")
}

android.namespace = "com.evo.permission"

dependencies {
    implementation(projects.feature.permission.api)
}