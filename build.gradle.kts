import org.gradle.kotlin.dsl.version
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin ("multiplatform")
    id("org.jetbrains.compose") version "1.4.0"
    id("app.cash.sqldelight") version "2.0.0-SNAPSHOT"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    maven(url = "https://oss.sonatype.org/content/repositories/snapshots")
}

kotlin {
    jvm {
        jvmToolchain {
            kotlin("1.8.20") // Replace with the appropriate Kotlin version
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.compose.material:material-icons-extended-desktop:1.3.0")
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-SNAPSHOT")
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "demo"
            packageVersion = "1.0.0"
        }
    }
}
