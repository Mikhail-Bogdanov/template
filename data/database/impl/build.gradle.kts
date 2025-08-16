plugins {
    id("evo-android")
}

android.namespace = "com.evo.database.impl"

dependencies {
    implementation(projects.data.database.api)
    implementation(projects.logger.api)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}
