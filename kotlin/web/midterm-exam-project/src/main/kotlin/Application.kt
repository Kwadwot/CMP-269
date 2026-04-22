package com.example

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.http.content.*
import kotlinx.serialization.Serializable
import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.nio.file.Paths
import java.util.concurrent.ConcurrentHashMap

// 1. DATA MODELING (Kotlin Fundamentals)
@Serializable
data class Student(
    val id: String,
    val name: String,
    val major: String?, // Nullable as per requirements
    val accessLevel: Int
)

fun saveQRCode(content: String, fileName: String): ByteArray {
    val bitMatrix: BitMatrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, 512, 512)

    val path = Paths.get(fileName)

    val outputStream = ByteArrayOutputStream()
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)
    return outputStream.toByteArray()
}

fun main() {
    // 2. THREAD-SAFE DATABASE
    val studentDb = ConcurrentHashMap<String, Student>().apply {
        put("12345", Student("12345", "Alice Smith", "Computer Science", 5))
        put("67890", Student("67890", "Bob Jones", null, 3)) // Will test Elvis operator
    }

    embeddedServer(Netty, port = 8080) {
        module(studentDb)
    }.start(wait = true)
}

fun Application.module(studentDb: ConcurrentHashMap<String, Student>) {

    // 3. CONTENT NEGOTIATION (JSON API)
    install(ContentNegotiation) {
        json()
    }

    routing {
        // A. STATIC PORTAL
        staticResources("/", "static")

        // B. STUDENT API (Path Parameters & Null Safety)
        get("/api/student/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.NotFound, mapOf("error" to "Missing or Invalid ID"))

            val student = studentDb[id] ?: return@get call.respond(HttpStatusCode.NotFound, mapOf("error" to "No student with id $id"))

            val response = mapOf(
                "id" to id,
                "name" to student.name,
                "major" to (student.major ?: "Undecided"),
                "accessLevel" to student.accessLevel.toString() // To handle Serializer error from different types
            )

            call.respond(HttpStatusCode.OK, response)
        }

        // C. QR GENERATOR (Query Parameters & Image Response)
        get("/generate-id") {
            val sid = call.request.queryParameters["sid"] ?: return@get call.respond(HttpStatusCode.BadRequest, "Missing parameter 'sid'")
            // NOT IMPLEMENTED:
            //
            // This endpoint would generate a QR code for the student ID,
            // demonstrating query parameters and image response handling.
            // Example usage:
            // 1. Visit: http://localhost:8080/
            // 2. Enter a student ID in the input field and click "Generate Digital ID".

            val student = studentDb[sid] ?: return@get call.respond(HttpStatusCode.NotFound, "Student not found: $sid")
            val content = "Student ID: $sid | Name: ${student.name} | Major: ${student.major ?: "Undeclared"}"

            val byteArray = saveQRCode(content, "student_id_${sid}.png")
            call.response.header(HttpHeaders.ContentType, "image/png")
            call.respondBytes(byteArray)
        }
    }
}
