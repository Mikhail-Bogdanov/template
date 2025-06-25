plugins {
    id("evo-android")
}

android.namespace = "com.evo.storage.impl"

dependencies {
    implementation(projects.data.storage.api)
    implementation(projects.data.internal)
    implementation(libs.data.store)
}
