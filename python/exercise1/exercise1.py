# CMP 269: Programming Methods III
# Python Introduction Exercises

def exercise_1_basics():
    """
    Goal: Practice basic syntax and string formatting.
    Task: Create variables for a course name and a number of students.
    Print a sentence using an f-string.
    """
    course = "CMP 269"
    students = 25
    print(f"The course {course} has {students} students.")

def exercise_2_collections():
    """
    Goal: Manipulate lists and dictionaries.
    Task:
    1. Create a list of 5 colors.
    2. Add a 6th color to the end.
    3. Create a dictionary with keys 'name' and 'gpa'.
    """
    print("List manipulation:")
    colors = ["magenta", "cyan", "yellow", "white", "black"]
    print(colors)
    colors.append("green")
    print(colors)

    print("\nDictionary manipulation:")
    report = {"name": "Abigail"}
    print(report)
    report["gpa"] = 95 # report.update({"gpa": 95})
    print(report)

def exercise_3_logic():
    """
    Goal: Use loops and conditionals.
    Task: Iterate through a list of numbers.
    If a number is even, add it to a new list called 'evens'.
    """
    nums = [x for x in range(25)]
    print(nums)

    evens = []

    for num in nums:
        if num % 2 == 0:
            evens.append(num)

    print(evens)


if __name__ == "__main__":
    print("--- Exercise 1 ---")
    exercise_1_basics()
    print("\n--- Exercise 2 ---")
    exercise_2_collections()
    print("\n--- Exercise 3 ---")
    exercise_3_logic()