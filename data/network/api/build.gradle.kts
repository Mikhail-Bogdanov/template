plugins {
    id("evo-api")
}

dependencies {
    implementation(libs.ktor.client.core)
    implementation(projects.logger.api)
}
