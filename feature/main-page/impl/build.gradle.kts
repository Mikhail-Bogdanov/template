plugins {
    id("feature")
}

android.namespace = "com.evo.mainPage.impl"

dependencies {
    implementation(projects.feature.mainPage.api)
}