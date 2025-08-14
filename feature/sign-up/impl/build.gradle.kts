plugins {
    id("feature")
}

android.namespace = "com.evo.signup"

dependencies {
    implementation(projects.feature.signUp.api)
}