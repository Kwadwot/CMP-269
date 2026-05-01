# Exercise 1: The "Lehman Course" Class
class LehmanCourse:
    def __init__(self, course_name = "", credits = 0, variable_student_count = 0):
        self.course_name = course_name
        self.credits = credits
        self._variable_student_count = variable_student_count # defaults to 0

    def enroll_student(self) -> None:
        self._variable_student_count += 1

    def display_info(self) -> None:
        print(f"Course Name: {self.course_name} | Credits: {self.credits} | Students Enrolled: {self._variable_student_count}")


# Exercise 2: Specialized Inheritance
class LabCourse(LehmanCourse):
    def __init__(self, course_name = "", credits = 0, variable_student_count = 0, lab_fee = 0.0):
        super().__init__(course_name, credits, variable_student_count)
        self.lab_fee = lab_fee

    def display_info(self) -> None:
        print(f"Course Name: {self.course_name} | Credits: {self.credits} | Students Enrolled: {self._variable_student_count} | Lab Fee: {self.lab_fee}")


# Exercise 3: Duck Typing Demonstration
class Person: # a parent class for the Professor and Student classes with basic properties
    def __init__(self, fname = "", lname = ""):
        self.fname = fname
        self.lname = lname

    def __str__(self):
        return f"{self.fname} {self.lname}"

class Professor(Person):
    def __init__(self, fname = "", lname = "", email = ""):
        super().__init__(fname, lname)
        self.email = email
        self.__courses_taught: list[LehmanCourse] = [] # private list courses taken to promote OOP relationship

    def get_role(self):
        return "Teaching and Research"

    def __str__(self):
        return f"Professor: {super().__str__()} | {self.email}"

class Student(Person):
    def __init__(self, fname = "", lname = "", email = ""):
        super().__init__(fname, lname)
        self.email = email
        self.__courses_taken: list[LehmanCourse] = [] # private list courses taken to promote OOP relationship

    def get_role(self):
        return "Learning and Coding"

    def __str__(self):
        return f"Student: {super().__str__()} | {self.email}"

def print_role(person: Person):
    return person.get_role()

if __name__ == "__main__":
    person1 = Professor("John", "Doe", "john.doe@lehman.cuny.edu")
    person2 = Student("Jane", "Doe", "jane.doe@lehman.cuny.edu")

    print(person1,"|", print_role(person1))
    print(person2, "|", print_role(person2))