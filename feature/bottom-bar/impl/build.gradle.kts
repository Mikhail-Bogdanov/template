plugins {
    id("feature")
}

android.namespace = "com.evo.bottombar"

dependencies {
    implementation(projects.feature.bottomBar.api)
    implementation(projects.feature.firstScreen.api)
    implementation(projects.feature.secondScreen.api)
}
