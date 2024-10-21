plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.dokka") version "1.8.20"
    id("maven-publish")
}

group = "dev.thelecrafter"
version = "2.0.0"

repositories {
    mavenCentral()
}

dependencies {
    api("org.jetbrains:annotations:24.0.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    explicitApi()
    jvmToolchain(17)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            pom {
                name.set(project.name)
                url.set("https://github.com/TheLeCrafter/CrafterMatrix")

                developers {
                    developer {
                        name.set("Blake")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "StckOverflwRepository"
            url = uri("https://maven.stckoverflw.net/releases")
            credentials(PasswordCredentials::class.java)
        }
    }
}