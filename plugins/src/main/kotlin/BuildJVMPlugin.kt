package ru.otus.otuskotlin.marketplace.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

@Suppress("unused")
class BuildJVMPlugin: Plugin<Project> {

    override fun apply(target: Project) = with(target){
        pluginManager.apply("org.jetbrains.kotlin.jvm")
        group = rootProject.group
        version = rootProject.version
    }
}