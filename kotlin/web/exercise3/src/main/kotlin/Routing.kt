package com.example

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.http.content.resources
import io.ktor.server.http.content.static
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            // --- Task 1 --- //
            call.respondText("Server is online at Lehman College.")
        }

        // --- Task 2 --- //
        get("/greet/{name}") {
            val name = call.parameters["name"] ?: call.respond(HttpStatusCode.NotFound)
            call.respondText("Hello, $name! Welcome to CMP 269.")
        }

        // --- Task 3 --- //
        get("/grade/{studentId}") {
            val grades = mapOf("123" to 95, "456" to 82)

            when (call.parameters["studentId"]) {
                "123" -> call.respond(HttpStatusCode.OK, grades.getValue("123"))
                "456" -> call.respond(HttpStatusCode.OK, grades.getValue("456"))
                else -> call.respond(HttpStatusCode.NotFound, "Student not found")
            }
        }

        // --- Task 4 --- //
        staticResources("/static", "static")
    }
}
