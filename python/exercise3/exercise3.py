# CMP 269: Programming Methods III
# In-Class Assignment: File I/O and API Integration

import requests
import json
import os

"""
INSTRUCTIONS:
Complete the following 5 tasks. Each task builds on the concepts 
of context managers (with statement), JSON parsing, and API communication.
"""

def task_1_append_logger():
    """
    TASK 1: The Persistent Logger
    Goal: Use the 'append' mode to add timestamps to a file.
    Instructions:
    1. Open a file named 'session_log.txt' in append mode ('a').
    2. Prompt the user for a short note.
    3. Write that note to the file on a new line.
    4. Read the entire file and print it to show the history.
    """
    print("--- Task 1: Append Logger ---")
    note = input("Enter a note for the log: ")
    try:
        with open('session_log.txt', 'a') as file:
            file.write(note)
    except FileNotFoundError:
        print(f"File not found: session_log.txt.")
    except PermissionError:
        print(f"Permission denied: Unable to append to session_log.txt.")


def task_2_word_count_utility():
    """
    TASK 2: The File Analyzer
    Goal: Read a file and perform basic data analysis.
    Instructions:
    1. Create a file 'lehman_motto.txt' with the text:
       "Knowledge is Power. Go Lightning! Python makes data easy."
    2. Read the file and count how many words are in it.
    3. Print the word count.
    """
    print("\n--- Task 2: Word Count Utility ---")
    try:
        with open('lehman_motto.txt', 'r') as file:
            text = file.read()
            word_count = len(text.split())
            print("Lehman motto word count:", word_count)
    except FileNotFoundError:
        print(f"File not found: lehman_motto.txt.")
    except PermissionError:
        print(f"Permission denied: Unable to append to lehman_motto.txt.")


def task_3_api_status_checker():
    """
    TASK 3: API Resilience
    Goal: Handle different HTTP status codes.
    Instructions:
    1. Attempt to fetch data from: https://jsonplaceholder.typicode.com/posts/101
    2. (Note: This ID might not exist or return a specific status).
    3. If status is 200, print the data.
    4. If status is 404, print "Error: Post not found."
    5. Use a try-except block to catch network timeout errors.
    """
    print("\n--- Task 3: API Status Checker ---")

    try:
        response = requests.get('https://jsonplaceholder.typicode.com/posts/101', timeout=5)
        response.raise_for_status()

        return response.json()
    except requests.exceptions.Timeout as timeout:
        print(f"Timeout error: {timeout}")
    except requests.exceptions.HTTPError:
        print(f"Error: Post not found.")
        return None
    except requests.exceptions.RequestException as exception:
        print(f"Error: {exception}")


def task_4_data_filtering():
    """
    TASK 4: JSON Data Processing
    Goal: Filter specific info from a JSON response.
    Instructions:
    1. Fetch a list of users from: https://jsonplaceholder.typicode.com/users
    2. Loop through the users and print only the names of users
       who live in a suite (check if 'suite' in the address contains "Suite").
    """
    print("\n--- Task 4: Data Filtering ---")
    try:
        response = requests.get('https://jsonplaceholder.typicode.com/users', timeout=5)
        response.raise_for_status()

        data = response.json()
        # print(data[1])
        for user in data:
            address = user['address']
            if "Suite" in address['suite']:
                print(user['username'])

    except requests.exceptions.Timeout as timeout:
        print(f"Timeout error: {timeout}")
    except requests.exceptions.HTTPError:
        print(f"Error: Data not found.")
        return None
    except requests.exceptions.RequestException as exception:
        print(f"Error: {exception}")


def task_5_integration_report():
    """
    TASK 5: The Integration Challenge
    Goal: Fetch API data and save it to a local file.
    Instructions:
    1. Fetch data from: https://jsonplaceholder.typicode.com/posts/1
    2. Extract the 'title' and 'body'.
    3. Save this information into a file named 'api_report.txt' in a
       clean, readable format.
    4. Print "Report Generated" once finished.
    """
    print("\n--- Task 5: Integration Report ---")
    try:
        response = requests.get('https://jsonplaceholder.typicode.com/posts/1', timeout=5)
        response.raise_for_status()

        data = response.json()
        title, body = data['title'], data['body']
        try:
            with open('api_report.txt', 'w') as file:
                file.write(title)
                file.write(body)
        except FileNotFoundError:
            print(f"File not found: api_report.txt.")
        except PermissionError:
            print(f"Permission denied: Unable to write to api_report.txt.")

    except requests.exceptions.Timeout as timeout:
        print(f"Timeout error: {timeout}")
    except requests.exceptions.HTTPError:
        print(f"Error: Data not found.")
        return None
    except requests.exceptions.RequestException as exception:
        print(f"Error: {exception}")


if __name__ == "__main__":
    # You can uncomment these as you complete them to test your code
    task_1_append_logger()
    task_2_word_count_utility()
    task_3_api_status_checker()
    task_4_data_filtering()
    task_5_integration_report()
    pass
