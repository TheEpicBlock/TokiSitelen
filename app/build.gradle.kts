import nl.theepicblock.ilopali.KamaGit
import nl.theepicblock.ilopali.SitelenKepekenSona

plugins {
    id("com.android.application")
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
}

val lipuLinkuSona by tasks.register<KamaGit>("lipuLinkuSona") {
    pokiPini = layout.buildDirectory.file("lipu_pi_insa_ala/sona").get().asFile
    lonKon = "https://github.com/lipu-linku/sona.git"
    lonInsa = "2bc26bd8372c7199607a65b536bf67d1642c3a8b"
}

tasks.register<SitelenKepekenSona>("sitelen") {
    pokiOpen = lipuLinkuSona.pokiPini
    pokiPini = layout.buildDirectory.get().file("lipu_pi_sin_pali/sitelen").asFile
}

tasks.create("kulupuSitelen", Jar::class) {
    from(tasks.getByName("sitelen").outputs)
}

dependencies {
    implementation(tasks.getByName("kulupuSitelen").outputs.files)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}