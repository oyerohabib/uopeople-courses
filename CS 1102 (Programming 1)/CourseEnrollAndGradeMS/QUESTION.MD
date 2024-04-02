# This assignment will assess your skills and knowledge to implement class methods and instance methods to define behavior and manipulate object state, and to apply access modifiers to control access to class members.

Context: You have been assigned to develop a Course Enrollment and Grade Management System in Java for a university. The system should provide functionality to enroll students in courses, assign grades to students, and calculate overall course grades for each student. The project should demonstrate the effective utilization of static methods and variables to keep track of enrollment and grade-related information across multiple instances of the Student and Course classes. It should also showcase your ability to manipulate object state and define behavior through instance methods.

Requirements:
Student Class:

The Student class should have private instance variables to store student information such as name, ID, and enrolled courses.
Implement appropriate access modifiers and provide public getter and setter methods for accessing and updating student information.
Design a method to enroll students in courses. It should accept a Course object as a parameter and add the course to the student's enrolled courses.
Implement a method to assign grades to students. It should accept a Course object and a grade for the student and update the student's grade for that course.
Course Class:

The Course class should have private instance variables to store course information such as course code, name, and maximum capacity.
Use appropriate access modifiers and provide public getter methods for accessing course information.
Implement a static variable to keep track of the total number of enrolled students across all instances of the Course class.
Design a static method to retrieve the total number of enrolled students.
CourseManagement Class:

The CourseManagement class should have private static variables to store a list of courses and the overall course grades for each student.
Use appropriate access modifiers to control access to the variables.
Implement static methods to add new courses, enroll students, assign grades, and calculate overall course grades for each student.
The addCourse method should accept parameters for course information and create a new Course object. It should add the course to the list of courses.
The enrollStudent method should accept a Student object and a Course object. It should enroll the student in the course by calling the appropriate method in the Student class.
The assignGrade method should accept a Student object, a Course object, and a grade. It should assign the grade to the student for that course by calling the appropriate method in the Student class.
The calculateOverallGrade method should accept a Student object and calculate the overall course grade for that student based on the grades assigned to them.
Administrator Interface:

Develop an interactive command-line interface for administrators to interact with the Course Enrollment and Grade Management System.
Display a menu with options to add a new course, enroll students, assign grades, and calculate overall course grades.
Prompt the administrator for necessary inputs and call the appropriate methods in the CourseManagement and Student classes to perform the requested operations.
Implement error handling to handle cases where invalid inputs are provided or when enrolling students in courses that have reached their maximum capacity.
Documentation:

Provide comprehensive documentation for the project, explaining the purpose and usage of each class, method, and variable.
Describe how static methods and variables are utilized to track enrollment and grade-related information across multiple instances of the Student and Course classes.
Include instructions for running the program and interacting with the administrator interface.
You will be assessed based on the following criteria:

Object oriented design - Object-oriented design in the Course Enrollment and Grade Management System project in Java allows for modular code organization, encapsulation of data and behavior within classes, and effective utilization of static methods and variables to track enrollment and grade-related information.
The Student Update feature in the Course Enrollment and Grade Management System project in Java enables administrators to efficiently modify student information such as name, ID, and enrolled courses, ensuring accurate and up-to-date data management.
The Course Update functionality in the Course Enrollment and Grade Management System project in Java allows administrators to easily update course information such as course code, name, and maximum capacity, ensuring flexibility and adaptability in managing course offerings.
The Course Management Update feature in the Course Enrollment and Grade Management System project in Java empowers administrators to efficiently add, modify, or remove courses, providing seamless control and customization of the curriculum offered by the university.
The Administrator Interface in the Course Enrollment and Grade Management System project in Java offers a user-friendly command-line interface that enables administrators to interactively perform various operations such as adding courses, enrolling students, assigning grades, and calculating overall course grades.
The documentation in the Course Enrollment and Grade Management System project in Java provides comprehensive instructions, explanations, and guidelines for the project's classes, methods, variables, and usage, ensuring clarity and facilitating ease of understanding and maintenance.
