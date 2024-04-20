plugins {
    alias(libs.plugins.kotlin.jvm) apply false
}

group = "com.otus.otuskotlin.marketplace"
version = "0.0.1"

repositories {
    mavenCentral()
}

subprojects {
    repositories{
        mavenCentral()
    }
    group = rootProject.group
    version = rootProject.version
}

tasks {
    create("check") {
        group = "verification"
        dependsOn(gradle.includedBuild("ok-notes-be").task(":check"))
    }
}
