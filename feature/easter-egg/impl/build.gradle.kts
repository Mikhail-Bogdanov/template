plugins {
    id("feature")
}

android.namespace = "com.evo.easteregg.impl"

dependencies {
    implementation(projects.feature.easterEgg.api)
}
