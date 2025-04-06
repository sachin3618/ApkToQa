plugins {
    id("org.jetbrains.kotlin.jvm") version "2.1.0"
    id("org.jetbrains.intellij.platform") version "2.4.0"
}

group = "com.codesharkstudio.apktoqa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

kotlin {
    jvmToolchain(17)
}

intellijPlatform {
    pluginConfiguration {
        name = "APKToQA"
        group = "com.codesharkstudio.apktoqa"
       // changeNotes.set(provider { recentChanges(HTML) })
        ideaVersion.sinceBuild.set("251.23774.16")
        ideaVersion.untilBuild.set(provider { null })
    }
    buildSearchableOptions.set(false)
    instrumentCode = true
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
dependencies{

    intellijPlatform {
        bundledPlugin("org.jetbrains.android")
        bundledPlugin("org.jetbrains.plugins.gradle")
        instrumentationTools()
        androidStudio("2025.1.1.2")

    }
}
//intellij {
//    version.set("191.8026.42")
//    type.set("IC") // Target IDE Platform
//
//    plugins.set(listOf("android"))
//}

tasks.runIde {
    jvmArgs = listOf("-Xmx4096m", "-XX:+UnlockDiagnosticVMOptions")
}

tasks {
    // Set the JVM compatibility versions
//    withType<JavaCompile> {
//        sourceCompatibility = "17"
//        targetCompatibility = "17"
//    }
//    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//        kotlinOptions.jvmTarget = "17"
//    }
//
//    patchPluginXml {
//        sinceBuild.set("241")
//        untilBuild.set("243.*")
//    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
