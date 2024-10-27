plugins {
    id("evo-application")
}

dependencies {
    implementation(projects.appEntrypoint)

    implementation(projects.presentationCommon.presentationCore)
    implementation(projects.presentationCommon.presentationExtensions)
    implementation(projects.presentationCommon.navigation)
    implementation(projects.presentationCommon.designSystem)

    implementation(projects.data.network.api)
    implementation(projects.data.network.impl)

    implementation(projects.data.theme.api)
    implementation(projects.data.theme.impl)

    implementation(projects.data.database.api)
    implementation(projects.data.database.impl)

    implementation(projects.data.datastore.api)
    implementation(projects.data.datastore.impl)

    implementation(projects.data.localStorage.api)
    implementation(projects.data.localStorage.impl)

    implementation(projects.data.userData.api)
    implementation(projects.data.userData.impl)

    implementation(projects.data.common.dataCore)
    implementation(projects.data.common.dataExtensions)
    implementation(projects.data.common.dataUtils)
}