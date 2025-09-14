plugins {
    id("feature")
}

android.namespace = "com.evo.whatsnew"

dependencies {
    implementation(projects.feature.whatsNew.api)
}