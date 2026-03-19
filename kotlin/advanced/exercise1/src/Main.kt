// --- Task 2.1 --- //
data class Laptop(val brand: String, val ramGB: Int)

// --- Task 2.3 --- //
fun Int.toLehmanGigabytes(): String {
    return "$this GB (Lehman Standard)"
}

// --- Task 3.1 --- //
sealed class EnrollmentStatus {
    data class Success(val courseCode: String): EnrollmentStatus()
    data class Error(val message: String = "Course not found"): EnrollmentStatus() // Added a default error message
    data class Loading(val message: String): EnrollmentStatus()
}

// --- Task 3.2 --- //
fun printStatus(status: EnrollmentStatus) {
    return when (status) {
        is EnrollmentStatus.Success -> println("Success")
        is EnrollmentStatus.Error -> println("Error")
        is EnrollmentStatus.Loading -> println("Loading")
    }
}

fun main() {

    // --- Task 1 --- //
    val studentName: String = "Kwadwo"
    var middleName: String? = null

    middleName = "P" // To test when middle name is not null

    val greeting: String = "Welcome, $studentName ${middleName?: "No Middle Name"}!"

    println("--- Exercise 1.1 ---")
    println(greeting)

    println("--------------------\n")


    // --- Task 2.2 --- //
    val laptop1 = Laptop("Lenovo", 16)
    val laptop2 = Laptop("Apple", 8)

    println("--- Exercise 1.2 ---")
    println(laptop1.ramGB.toLehmanGigabytes())
    println(laptop2.ramGB.toLehmanGigabytes())

    println("--------------------\n")


    // --- Task 3.4 --- //
    val status1: EnrollmentStatus = EnrollmentStatus.Success("CMP 269")
    val status2: EnrollmentStatus = EnrollmentStatus.Error()

    println("--- Exercise 1.3 ---")
    printStatus(status1)
    printStatus(status2)

    println("--------------------\n")
    println("-Exercise Complete-")

    // Added print statements for better readability in terminal
}
