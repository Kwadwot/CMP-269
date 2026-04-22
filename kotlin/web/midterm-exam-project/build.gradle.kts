plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.example"
version = "0.0.1"

application {
    // mainClass = "io.ktor.server.netty.EngineMain"
    mainClass.set("com.example.ApplicationKt")
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    // Ktor server and Netty engine
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)

    // Content Negotiation for JSON serialization
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)

    // ZXing dependencies for QR code generation
    implementation(libs.zxing.core)
    implementation(libs.zxing.javase)

    // Logging
    implementation(libs.logback.classic)
    // implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
