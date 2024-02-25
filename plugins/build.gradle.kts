plugins {
    `kotlin-dsl`
}

gradlePlugin{
    plugins{
        register("build-jvm"){
            id = "build-jvm"
            implementationClass = "ru.otus.otuskotlin.marketplace.plugin.BuildJVMPlugin"
        }

        register("build-mp"){
            id = "build-mp"
            implementationClass = "ru.otus.otuskotlin.marketplace.plugin.BuildKMPPlugin"
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
    implementation(libs.plugin.kotlin)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}