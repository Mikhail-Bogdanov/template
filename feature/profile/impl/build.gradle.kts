plugins {
    id("feature")
}

android.namespace = "com.evo.profile"

dependencies {
    implementation(projects.feature.profile.api)
}