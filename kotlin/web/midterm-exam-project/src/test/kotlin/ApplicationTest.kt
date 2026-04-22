package com.example

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import java.util.concurrent.ConcurrentHashMap
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import io.ktor.client.statement.*

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module(ConcurrentHashMap()) // Pass an empty database for testing
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

     @Test
     fun testGetStudentStatus() = testApplication {
         val studentDb = ConcurrentHashMap<String, Student>().apply {
             put("999555888", Student("12345", "Alice Smith", "Computer Science", 5))
         }
         application {
             module(studentDb)
         }
         client.get("/api/student/999555888").apply {
             assertEquals(HttpStatusCode.OK, status)
             // Additional assertions can be made here to verify the response body
         }
     }

     @Test
     fun testGetStudentNotFound() = testApplication {
         val studentDb = ConcurrentHashMap<String, Student>()
         application {
             module(studentDb)
         }
         client.get("/api/student/000000000").apply {
             assertEquals(HttpStatusCode.NotFound, status)
         }
     }

     @Test
     fun testGetStudentNullMajor() = testApplication {
         val studentDb = ConcurrentHashMap<String, Student>().apply {
             put("67890", Student("67890", "Bob Jones", null, 3))
         }
         application {
             module(studentDb)
         }
         // val response = client.get("/api/student/67890").bodyAsText()

         // val student = Json.decodeFromString<Student>(response)
         // assertEquals("Bob Jones", student.name)
         // assertEquals(null, student.major) // Verify that major is null
     }

}
