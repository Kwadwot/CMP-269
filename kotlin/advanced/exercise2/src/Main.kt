// --- Task 1.1 and 1.2 --- //
data class WebResponse(val statusCode: Int, val statusMessage: String, val body: String?)

// --- Task 1.3 --- //
fun main(args: Array<String>) {

    val successRes = WebResponse(200, "Ok", null)
    val notFoundRes = WebResponse(404, "Not Found", null)

    // --- Task 1.4 --- //
    println("--------------------- Task 1 ---------------------")
    println(successRes)
    println(notFoundRes)
    println("--------------------------------------------------")

}