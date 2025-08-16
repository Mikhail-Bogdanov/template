plugins {
    id("evo-compose")
}

android.namespace = "com.evo.navigation.impl"

dependencies {
    implementation(projects.navigation.api)
    implementation(projects.feature.firstScreen.api)
    implementation(projects.data.storage.api)
    implementation(projects.coroutine)
}
