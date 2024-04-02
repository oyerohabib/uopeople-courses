# Course and Student Management System Documentation

## Overview

This Java program implements a simple Course and Student Management System that allows administrators to perform various tasks such as adding courses, enrolling students, assigning grades, and calculating overall course grades. The system consists of three main classes: `Student`, `Course`, and `CourseManagement`, along with an `AdministratorInterface` class for user interaction.

## Class Descriptions

### 1. `Student` Class

The `Student` class represents a student in the system. Each student has a name, a unique student ID, a list of enrolled courses, and corresponding grades. Key functionalities of this class include:

- **Constructor:** Initializes a new student with a name and a student ID. Creates empty lists for enrolled courses and grades.

- **Enrollment:** Allows a student to enroll in a course, ensuring the student is not already enrolled and that the maximum enrollment limit (5 courses) is not exceeded.

- **Grade Assignment:** Assigns grades to students for specific courses and calculates the overall grade.

- **Getters and Setters:** Provides methods to retrieve and modify student attributes.

### 2. `Course` Class

The `Course` class represents a course that students can enroll in. Each course has a unique course code, a name, a maximum capacity for students, and a list of enrolled students with corresponding grades. Key functionalities include:

- **Constructor:** Initializes a new course with a course code, name, and maximum capacity. Creates an empty list for enrolled students.

- **Enrollment:** Allows a student to enroll in a course, ensuring the maximum capacity is not exceeded.

- **Grade Assignment:** Assigns grades to individual students for the course.

- **Getters:** Provides methods to retrieve course attributes and enrolled students.

### 3. `CourseManagement` Class

The `CourseManagement` class serves as a central hub for managing courses and students. It contains static methods for adding courses, enrolling students, assigning grades, and calculating overall grades. Key functionalities include:

- **Course and Student Lists:** Maintains static lists of courses and students for easy access.

- **Course Addition:** Adds a new course to the system with a specified course code, name, and maximum capacity.

- **Student Enrollment:** Enrolls a student in a course, checking for maximum capacity and existence of the student.

- **Grade Assignment:** Assigns grades to students for specific courses.

- **Overall Grade Calculation:** Calculates the overall grade for a student based on enrolled courses.

- **Getters:** Provides methods to retrieve lists of courses and students.

### 4. `AdministratorInterface` Class

The `AdministratorInterface` class serves as the user interface for administrators to interact with the system. It includes a menu-driven interface that allows administrators to perform various tasks:

- **Add New Course:** Takes user input to add a new course to the system.

- **Enroll Student:** Enrolls a student in a course, taking into account the available courses and maximum capacity.

- **Assign Grade:** Assigns a grade to a student for a specific course.

- **Calculate Overall Grade:** Calculates and displays the overall grade for a student.

- **Exit Program:** Exits the program when the user chooses to do so.

## Usage

1. **Adding a Course:**

   - Select option 1 in the menu.
   - Enter the course code, name, and maximum capacity.

2. **Enrolling a Student:**

   - Select option 2 in the menu.
   - Enter the student's name and ID.
   - Choose a course from the available list.

3. **Assigning a Grade:**

   - Select option 3 in the menu.
   - Enter the student's ID.
   - Choose a course from the available list.
   - Enter the grade for the student.

4. **Calculating Overall Grade:**

   - Select option 4 in the menu.
   - Enter the student's ID.

5. **Exiting the Program:**
   - Select option 0 in the menu.

## Error Handling

- The program includes basic error handling to ensure valid inputs. For example, it checks for valid course codes, non-empty names, and numeric values within specified ranges for grades.

- InputMismatchException handling is implemented to capture invalid user inputs.

## Notes

- The system has a maximum enrollment limit of 5 courses per student.

- Students must be enrolled in a course before grades can be assigned.

- Overall grades are calculated based on the average of grades in all enrolled courses.

- The system maintains static lists of courses and students, allowing easy retrieval and management.

This Course and Student Management System provides a foundation for academic institutions to manage student enrollments, course assignments, and grading efficiently. Users can interact with the system through the administrator interface to perform various tasks seamlessly.
