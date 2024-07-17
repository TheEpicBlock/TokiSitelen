repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.9.0.202403050737-r")
    implementation("org.tomlj:tomlj:1.1.1")
    implementation("com.android.tools:sdk-common:31.2.0") {
        exclude("com.android.tools.analytics-library", "shared")
    }
    implementation("nl.theepicblock:kepekenAle")
}