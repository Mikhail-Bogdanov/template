plugins {
    id("evo-compose")
}

android.namespace = "com.evo.profile.api"

dependencies {
    implementation(projects.navigation.api)
}
