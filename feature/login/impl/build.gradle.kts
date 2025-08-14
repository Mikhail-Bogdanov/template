plugins {
    id("feature")
}

android.namespace = "com.evo.login"

dependencies {
    implementation(projects.feature.login.api)
}