rootProject.name = "ok-notes-be"

dependencyResolutionManagement{
    versionCatalogs{
        create("libs"){
            from(files("../gradle/libs.versions.toml"))
        }
    }
}


pluginManagement {
    plugins {
        includeBuild("../plugins")
        plugins{
            id("build-jvm") apply false
            id("build-kmp") apply false
        }
        repositories{
            mavenCentral()
            gradlePluginPortal()
        }
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

