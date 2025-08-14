import java.util.Properties

plugins {
    id("evo-android")
}

android.namespace = "com.evo.network.impl"

dependencies {
    implementation(projects.data.network.api)
    implementation(projects.data.internal)
    implementation(libs.bundles.network)
}

android {
    defaultConfig {
        val properties = Properties()
        val configFile = rootProject.file("config.properties")
        if (configFile.exists()) {
            properties.load(configFile.inputStream())

            buildConfigField("String", "EVO_BASE_URL", properties["EVO_BASE_URL"].toString())
        }
    }
}
