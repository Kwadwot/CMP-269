package com.example

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.ByteArrayOutputStream
import java.nio.file.Paths

// --- Task 1 --- //
fun saveQRCode(content: String, fileName: String): ByteArray {
    val bitMatrix: BitMatrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, 512, 512)

    val path = Paths.get(fileName)

    // MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path)

    // --- Task 2 --- //
    val outputStream = ByteArrayOutputStream()
    MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream)
    return outputStream.toByteArray()
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("HELLO WORLD!")
        }

        // --- Task 3 --- //
        get("/qr") {
            val text = call.request.queryParameters["text"]

            if (text != null) {
                val byteArray = saveQRCode(text, "my_email.png")
                call.response.header(HttpHeaders.ContentType, "image/png")
                call.respondBytes(byteArray)
            } else {
                call.respondText("NOT FOUND!", status = HttpStatusCode.NotFound)
            }
        }
    }
}
