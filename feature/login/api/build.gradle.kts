plugins {
    id("evo-compose")
}

android.namespace = "com.evo.login.api"

dependencies {
    implementation(projects.navigation.api)
}
