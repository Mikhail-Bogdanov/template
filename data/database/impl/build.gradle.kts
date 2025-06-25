plugins {
    id("evo-android")
}

android.namespace = "com.evo.database.impl"

dependencies {
    implementation(projects.data.database.api)
    implementation(projects.data.internal)
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)
}
