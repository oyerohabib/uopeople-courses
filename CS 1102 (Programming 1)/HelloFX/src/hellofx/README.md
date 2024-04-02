# Course Management System

The Course Management System is a JavaFX-based application designed to manage courses, students, and their interactions through a graphical user interface (GUI). This README provides comprehensive documentation for the project, explaining the purpose and usage of each GUI component, event handler, and functionality.

## Purpose

The system allows users to perform various operations, including adding a new course, enrolling students, assigning grades, calculating overall grades, and viewing lists of students and courses.

## Usage

### Main Window

The main window displays a menu with several options for managing courses and students. Buttons include:

- **Add New Course:** Opens a dialog to add a new course.
- **Enroll Student:** Opens a dialog to enroll a student in a course.
- **Assign Grade:** Opens a dialog to assign a grade to a student for a specific course.
- **Calculate Overall Grade:** Opens a dialog to calculate and display the overall grade for a student.
- **View Student List:** Opens a new window displaying a table with a list of students. Users can update student details from this window.
- **View Course List:** Opens a new window displaying a table with a list of courses.

### Add New Course Dialog

This dialog allows users to add a new course. Fields include:

- Course Code: Text field for entering the course code.
- Course Name: Text field for entering the course name.
- Max Capacity: Text field for entering the maximum capacity of the course.
  Clicking the "Add Course" button adds the new course to the system.

### Enroll Student Dialog

This dialog allows users to enroll a student in a course. Fields include:

- Student Name: Text field for entering the student's name.
- Student ID: Text field for entering the student's ID.
- Select Course: Dropdown for selecting the course to enroll in.
  Clicking the "Enroll" button enrolls the student in the selected course.

### Assign Grade Dialog

This dialog allows users to assign a grade to a student for a specific course. Fields include:

- Student ID: Text field for entering the student's ID.
- Select Course: Dropdown for selecting the course.
- Grade: Text field for entering the grade.
  Clicking the "Assign Grade" button assigns the specified grade to the student for the selected course.

### Calculate Overall Grade Dialog

This dialog allows users to calculate and display the overall grade for a student. Fields include:

- Student ID: Text field for entering the student's ID.
  Clicking the "Calculate Overall Grade" button calculates and displays the overall grade for the student.

### View Student List

This window displays a table with a list of students. Columns include:

- Name: Student's name.
- Student ID: Student's ID.
- Enrolled Courses: Courses in which the student is enrolled.
- Action: Allows updating student details by clicking the "Update" button.

### Update Student Dialog

This dialog allows users to update details for a selected student. Fields include:

- Name: Text field for updating the student's name.
- Student ID: Disabled text field displaying the student's ID (non-editable).
- Additional fields can be added as needed.
  Clicking the "Update" button updates the student's details.

### View Course List

This window displays a table with a list of courses. Columns include:

- Course Code: Code assigned to the course.
- Course Name: Name of the course.
- Max Capacity: Maximum capacity of the course.

## Design Choices

- **JavaFX Components:** Utilizes JavaFX for creating a modern and responsive GUI.
- **GridPane Layout:** Organizes components in a grid layout for a clean and structured appearance.
- **Dialog Windows:** Utilizes separate dialog windows for specific actions to improve user experience and maintain a clear workflow.
- **TableView:** Used for displaying lists of students and courses, providing a tabular view.
- **Alerts:** Information, warning, and error alerts provide feedback to the user based on their actions.

## Running the Program

1. **IDE Setup:** Ensure you have Java Development Kit (JDK) installed, and your IDE (in this case, VSCode) is configured for Java development.
2. **Compile and Run:** Open the provided Java files in VSCode. Compile and run the `CourseManagementGUI.java` file.
3. **Interacting with the GUI:** Upon running, the main window will appear with various buttons for different operations. Click on buttons to perform respective actions such as adding a new course, enrolling a student, assigning a grade, calculating overall grades, and viewing lists.

## Note

- **Data Storage:** The application does not persist data. Data is stored in memory and will be lost upon closing the program.

This Course Management System provides an intuitive and user-friendly interface for managing courses and students, promoting ease of use and efficient interaction.
