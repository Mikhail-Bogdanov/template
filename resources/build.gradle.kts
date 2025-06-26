plugins {
    id("evo-compose")
}

android.namespace = "com.evo.resources"

dependencies {
    implementation(projects.data.storage.api)
}
