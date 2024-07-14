pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Toki Sitelen"
include(":app")

includeBuild("kepekenAle") {
    dependencySubstitution {
        substitute(module("nl.theepicblock:kepekenAle")).using(project(":"))
    }
}