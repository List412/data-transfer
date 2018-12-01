import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.0"
}

group = "data-transferer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("org.jetbrains.exposed:exposed:0.11.2")
    compile("mysql:mysql-connector-java:5.1.46")
    compile("org.xerial:sqlite-jdbc:3.25.2")
    compile(kotlin("script-runtime"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}