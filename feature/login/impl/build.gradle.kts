plugins {
    id("feature")
}

android.namespace = "com.evo.login.impl"

dependencies {
    implementation(projects.feature.login.api)
}
