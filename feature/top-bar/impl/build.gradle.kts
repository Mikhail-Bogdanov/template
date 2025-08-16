plugins {
    id("feature")
}

android.namespace = "com.evo.topbar.impl"

dependencies {
    implementation(projects.feature.topBar.api)
}
