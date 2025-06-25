plugins {
    id("screen")
}

android.namespace = "com.evo.presentation"

dependencies {
    implementation(projects.data.storage.api)
    implementation(projects.resources)
}
