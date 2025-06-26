plugins {
    id("feature")
}

android.namespace = "com.evo.signup.impl"

dependencies {
    implementation(projects.feature.signUp.api)
}