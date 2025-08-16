plugins {
    id("evo-compose")
}

android.namespace = "com.evo.signup.api"

dependencies {
    implementation(projects.navigation.api)
}
