plugins {
    id("feature")
}

android.namespace = "com.evo.permission.impl"

dependencies {
    implementation(projects.feature.permission.api)
}
