import com.evo.customplugins.extensions.booleanField

plugins {
    id("evo-android")
}

android {
    namespace = "com.evo.logger.impl"

    buildFeatures {
        buildConfig = true
        buildTypes {
            debug {
                booleanField("isLoggingEnabled", true)
            }
            release {
                booleanField("isLoggingEnabled", false)
            }
        }
    }
}
