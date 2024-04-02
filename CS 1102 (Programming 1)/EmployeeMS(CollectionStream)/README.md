# Employee Management System

The Employee Management System is a Java program that demonstrates the use of Java streams and the Function interface to process and manipulate a dataset of employees in a company.

## Employee Class

The `Employee` class represents the data structure for an employee in the company. It includes private fields for storing attributes such as name, age, department, and salary. The class provides a constructor to initialize an employee object, getter methods for retrieving individual attributes, and overrides the `toString()` method to provide a custom string representation of the employee.

### Constructor

```java
public Employee(String name, int age, String department, double salary)
```

Creates a new `Employee` object with the specified attributes.

### Getter Methods

- `public String getName()`: Retrieves the name of the employee.
- `public int getAge()`: Retrieves the age of the employee.
- `public String getDepartment()`: Retrieves the department of the employee.
- `public double getSalary()`: Retrieves the salary of the employee.

### Custom String Representation

The `toString()` method is overridden to provide a custom string representation of the employee.

## EmployeeProcessing Class

The `EmployeeProcessing` class contains the main method to demonstrate the functionality of the `Employee` class and Java streams for processing the dataset of employees.

### Main Method

```java
public static void main(String[] args)
```

The main method performs the following operations:

1. Creates and populates the dataset of employees.
2. Defines a `Function` interface for concatenating employee names and departments.
3. Uses streams to generate a new collection of concatenated strings.
4. Calculates the average salary using stream's built-in functions.
5. Generalizes the program with a filter function for employees above a certain age.
6. Sorts employees by salary in descending order.
7. Filters employees by department.
8. Calculates total salary expenditure.
9. Groups employees by department.
10. Displays employee statistics.

## How to Use

1. Open a terminal and navigate to where you have the code file.
2. Compile the Code using `javac index.java`
3. Run the compile code using `java EmployeeProcessing` to see the program in action.
