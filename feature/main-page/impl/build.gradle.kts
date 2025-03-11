plugins {
    id("feature")
}

android.namespace = "com.evo.mainpage.impl"

dependencies {
    implementation(projects.feature.mainPage.api)
}