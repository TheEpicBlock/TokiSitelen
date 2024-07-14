import nl.theepicblock.ilopali.KamaGit
import nl.theepicblock.ilopali.PonaESona

plugins {
    id("com.android.application")
}

val sonaLinku by tasks.register<KamaGit>("sonaLinku") {
    pokiPini = layout.buildDirectory.file("lipu_pi_insa_ala/sona").get().asFile

    lonKon = "https://github.com/lipu-linku/sona.git"
    lonInsa = "2bc26bd8372c7199607a65b536bf67d1642c3a8b"
}

val ijoLinku by tasks.register<KamaGit>("ijoLinku") {
    pokiPini = layout.buildDirectory.file("lipu_pi_insa_ala/ijo").get().asFile

    lonKon = "https://github.com/lipu-linku/ijo.git"
    lonInsa = "dd0208a20bd0b4817eba4115da0ef244bf2961a0"
}

val lipuNimi by tasks.register<PonaESona>("lipuNimi") {
    pokiPini = layout.buildDirectory.get().file("lipu_pi_sin_pali/lipuNimi").asFile

    pokiSona = sonaLinku.pokiPini
    pokiIjo = ijoLinku.pokiPini
}

android {
    namespace = "nl.theepicblock.tokisitelen"
    compileSdk = 34

    defaultConfig {
        applicationId = "nl.theepicblock.tokisitelen"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    applicationVariants.all {
        registerGeneratedResFolders(tasks.getByName("lipuNimi").outputs.files)
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("nl.theepicblock:kepekenAle")
}