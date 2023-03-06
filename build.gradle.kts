import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    id("java")
    application
}

group = "me.fn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    google()
    maven {
        url = uri("https://repo.powbot.org/releases")
    }
}

dependencies {
    implementation("org.powbot:client-sdk:1.+")
    implementation("org.powbot:client-sdk-loader:1.+")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.google.protobuf:protobuf-java:3.10.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}