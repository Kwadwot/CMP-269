# CMP 269: Programming Methods III
#
# This exercise is already completed. Use it as a reference to recreate Java Exercise 1
#
# The Scenario: "Lehman Campus Payment System"
# The college needs a new system to handle different types of payments (Credit Cards, Meal # # Plans, and Financial Aid). You are tasked with building the class hierarchy for this system.
from abc import abstractmethod, ABC


class Payable(ABC):
    @abstractmethod
    def process_payment(self, amount: float) -> None:
        pass

    @abstractmethod
    def get_payment_status(self) -> str:
        pass

class PaymentMethod(Payable):
    total_transactions = 0

    def __init__(self, account_holder = "", balance = 0.0):
        self._account_holder = account_holder
        self._balance = balance

    @abstractmethod
    def validate_account(self) -> None:
        pass

class CreditCard(PaymentMethod):
    def __init__(self, acct_holder: str, balance: float, credit_limit: float):
        super().__init__(acct_holder, balance)
        self.__credit_limit = credit_limit

    def validate_account(self) -> None:
        pass

    def process_payment(self, amount: float) -> None:
        if amount > self._balance + self.__credit_limit:
            print("Transaction Declined")
        else:
            self._balance -= amount
            PaymentMethod.total_transactions += 1

    def get_payment_status(self) -> str:
        return ""

class MealPlan(PaymentMethod):
    def __init__(self, acct_holder: str, balance: float):
        super().__init__(acct_holder, balance)

    def validate_account(self) -> None:
        if self._balance < 0:
            self._balance = 0

    def process_payment(self, amount: float) -> None:
        if amount > self._balance:
            print("Transaction Declined")
        else:
            self._balance -= amount
            PaymentMethod.total_transactions += 1

    def get_payment_status(self) -> str:
        return ""




class Exercise1_Student:
    """
    Goal: Practice class definition, __init__, and the self keyword.
    """
    def __init__(self, name, gpa):
        self.name = name
        self.gpa = gpa
        # Using a single underscore denotes a "protected" or internal variable
        self._is_active = True

    def get_status(self):
        status = "Active" if self._is_active else "Inactive"
        return f"{self.name} is currently {status} with a {self.gpa} GPA."

class Exercise2_GradStudent(Exercise1_Student):
    """
    Goal: Practice Inheritance and super().
    """
    def __init__(self, name, gpa, research_lab):
        # Call the parent class constructor
        super().__init__(name, gpa)
        self.research_lab = research_lab

    # Overriding the parent method
    def get_status(self):
        base_status = super().get_status()
        return f"{base_status} They research in the {self.research_lab} lab."

class Robot:
    def get_status(self):
        return "BEEP BOOP. Robot systems nominal."

def exercise_3_polymorphism():
    """
    Goal: Demonstrate Duck Typing.
    Notice how this function doesn't care about the object's class,
    only that it has a 'get_status()' method!
    """
    print("\n--- Exercise 3: Polymorphism (Duck Typing) ---")

    undergrad = Exercise1_Student("Alice", 3.5)
    grad = Exercise2_GradStudent("Bob", 3.9, "AI Data")
    bot = Robot()

    # We can put completely unrelated objects in a list
    entities = [undergrad, grad, bot]

    for entity in entities:
        # As long as it "quacks" (has get_status()), Python executes it!
        print(entity.get_status())

if __name__ == "__main__":
    # Polymorphism in Action Test:
    payment_queue: list[Payable] = []
    payment_queue.append(CreditCard("KPT", 5000, 10000))
    payment_queue.append(MealPlan("KT", 300))

    for payable in payment_queue:
        payable.process_payment(50.0)

    # Checking that totalTransactions is properly incremented
    print(f"Number of transactions: {PaymentMethod.total_transactions}") # Output: Number of transactions: 2

