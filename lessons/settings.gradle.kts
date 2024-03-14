pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        kotlin("jvm") version kotlinVersion
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "lessons"

include("m1l1-first")
include("m1l3-second")
include("m1l4")
include("m1l5-dsl")
include("m2l5-gradle")

include(":m2l5-gradle:sub1:subb1", ":m2l5-gradle:sub1:subb2")
include(":m2l5-gradle-sub2")
project(":m2l5-gradle-sub2").apply{
    projectDir = file("m2l5-gradle/sub2")
    name = "custom-sub2"

}
