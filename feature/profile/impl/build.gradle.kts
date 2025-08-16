plugins {
    id("feature")
}

android.namespace = "com.evo.profile.impl"

dependencies {
    implementation(projects.feature.profile.api)
}
