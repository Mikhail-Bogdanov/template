plugins {
    id("evo-compose")
}

android.namespace = "com.evo.navigation.impl"

dependencies {
    implementation(projects.navigation.api)
    implementation(projects.feature.login.api)
    implementation(projects.feature.update.api)
    implementation(projects.feature.startScreen.api)
    implementation(projects.feature.whatsNew.api)
    implementation(projects.data.storage.api)
    implementation(projects.coroutine)
}
