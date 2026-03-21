// --- Task 1.1 and 1.2 --- //
data class WebResponse(val statusCode: Int, val statusMessage: String, val body: String?)

// --- Task 2.1 --- //
fun descrideStatus(code: Int): String {

    // --- Task 2.2 --- //
    when (code) {
        in 200..299 -> return "Success: The request was fulfilled."
        in 400..499 -> return "Client Error: Check your URL or parameters."
        in 500..599 -> return "Server Error: The Lehman Server is having trouble."
        else -> return "Unknown status code."
    }
}

// --- Task 3.1 --- //
fun routeRequest(path: String, user: String?): String {

    // --- Task 3.2 --- //
    when (path) {
        "/home" -> return "Welcome to the Lehman Homepage, ${user ?: "Guest"}!"
        "/grades" -> return if (user != null) {
            // --- Task 3.3 --- //
            "Loading grades for ${user ?: "User"}..."
        } else {
            "Error: Unauthorized access to grades."
        }
        else -> return "404: Path $path not found."
    }
}

// --- Task 1.3 --- //
fun main(args: Array<String>) {

    val successRes = WebResponse(200, "Ok", null)
    val notFoundRes = WebResponse(404, "Not Found", null)

    println("------------------- Exercise 2 -------------------\n")
    // --- Task 1.4 --- //
    println("--------------------- Task 1 ---------------------")
    println(successRes)
    println(notFoundRes)
    println("--------------------------------------------------\n")

    // --- Task 2.3 --- //
    println("--------------------- Task 2 ---------------------")
    println(descrideStatus(201))
    println(descrideStatus(404))
    println(descrideStatus(503))
    println("--------------------------------------------------\n")

    // --- Task 3.4 --- //
    println("--------------------- Task 3 ---------------------")
    println(routeRequest("/home", null))
    println(routeRequest("/home", "Kwadwo T"))
    println(routeRequest("/grades", "Kwadwo T"))
    println(routeRequest("/grades", null))
    println(routeRequest("/about", null))
    println("--------------------------------------------------\n")

    println("-------------- Exercise 2 Complete! --------------")

}