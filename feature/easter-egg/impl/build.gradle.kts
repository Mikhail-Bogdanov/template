plugins {
    id("feature")
}

android.namespace = "com.evo.easteregg"

dependencies {
    implementation(projects.feature.easterEgg.api)
}