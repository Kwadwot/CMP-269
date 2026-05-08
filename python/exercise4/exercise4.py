# CMP 269: Programming Methods III
# In-Class Assignment: Pandas Series and DataFrames

import pandas as pd

"""
INSTRUCTIONS:
Complete the following 4 tasks using the pandas library. 
Run your script frequently to see how the DataFrames look in the console!
"""

def task_1_series_creation():
    """
    TASK 1: Create a Series
    1. Create a dictionary mapping 4 Lehman building names to their floor counts.
       (e.g., "Gillet": 4, "Carman": 3, "Music": 3, "Library": 4)
    2. Convert this dictionary into a Pandas Series.
    3. Print the Series.
    """
    print("--- Task 1: Building Series ---")

    buildings_dict = {"Gillet": 4, "Carman": 3, "Music": 3, "Library": 4, "Apex": 3, "Student Life": 2}

    buildings_series = pd.Series(buildings_dict)

    print("\nBuildings Series:")
    print(buildings_series)


def task_2_dataframe_creation():
    """
    TASK 2: Create a DataFrame
    1. Create a dictionary of lists containing data for at least 3 courses:
       - 'CourseCode': ['CMP168', 'CMP269', 'CMP338']
       - 'Credits': [4, 4, 4]
       - 'Enrolled': [25, 30, 20]
    2. Convert this into a Pandas DataFrame.
    3. Print the DataFrame.
    """
    print("\n--- Task 2: Course DataFrame ---")

    course_dict = {'CourseCode': ['CMP168', 'CMP269', 'CMP338', 'ENG210', 'MSP185', 'LSP351'],
                   'Credits': [4, 4, 4, 3, 1, 3],
                   'Enrolled': [25, 30, 20, 36, 5, 18]}

    course_df = pd.DataFrame(course_dict)

    print("\nCourse DataFrame:")
    print(course_df)



def task_3_data_manipulation():
    """
    TASK 3: Filtering and Math
    1. Using the same data from Task 2, create the DataFrame here again.
    2. Filter the DataFrame to only show courses with more than 20 students enrolled.
    3. Calculate and print the total number of students across ALL courses (use the .sum() method).
    """
    print("\n--- Task 3: Filtering and Math ---")

    course_dict = {'CourseCode': ['CMP168', 'CMP269', 'CMP338', 'ENG210', 'MSP185', 'LSP351'],
                   'Credits': [4, 4, 4, 3, 1, 3],
                   'Enrolled': [25, 30, 20, 36, 5, 18]}

    course_df = pd.DataFrame(course_dict)

    filtered_courses = course_df[course_df['Enrolled'] > 20]
    total_students = course_df['Enrolled'].sum()

    print("\nFiltered courses:")
    print(filtered_courses)
    print("\nTotal students:")
    print(total_students)


def task_4_csv_integration():
    """
    TASK 4: The Pandas CSV Advantage
    1. Create a simple DataFrame representing stock data (Symbols and Prices).
    2. Use df.to_csv('stocks.csv', index=False) to save it.
    3. Use pd.read_csv('stocks.csv') to read it back into a new variable called df_loaded.
    4. Print df_loaded to prove it worked!
    """
    print("\n--- Task 4: Easy CSV I/O ---")
    stock_data = {
        'Symbol': ['AAPL', 'GOOGL', 'MSFT', 'AMZN', 'TSLA', 'META', 'NVDA'],
        'Price':  [189.45, 175.82, 415.30, 182.67, 177.90, 502.15, 875.40]
    }

    stock_df = pd.DataFrame(stock_data)
    stock_df.to_csv('stocks.csv', index=False)

    df_loaded = pd.read_csv('stocks.csv')
    print(df_loaded)


if __name__ == "__main__":
    # Uncomment these as you work through the assignment
    # task_1_series_creation()
    # task_2_dataframe_creation()
    # task_3_data_manipulation()
    task_4_csv_integration()
    pass

