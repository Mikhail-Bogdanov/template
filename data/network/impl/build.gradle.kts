import java.util.Properties

plugins {
    id("evo-android")
}

android {
    namespace = "com.evo.network.impl"
    
    buildFeatures.buildConfig = true

    defaultConfig {
        val properties = Properties()
        val configFile = rootProject.file("config.properties")
        if (configFile.exists()) {
            properties.load(configFile.inputStream())

            buildConfigField("String", "EVO_BASE_URL", properties["EVO_BASE_URL"].toString())
        }
    }
}

dependencies {
    implementation(projects.data.network.api)
    implementation(projects.logger.api)
    implementation(libs.bundles.network)
}
