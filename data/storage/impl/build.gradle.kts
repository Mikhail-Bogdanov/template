plugins {
    id("evo-android")
}

android.namespace = "com.evo.storage.impl"

dependencies {
    implementation(projects.data.storage.api)
    implementation(projects.logger.api)
    implementation(libs.data.store)
}
