plugins {
    kotlin("jvm") version "1.8.20"
    id("maven-publish")
}

group = "dev.thelecrafter"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
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