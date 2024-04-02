package hellofx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.scene.layout.VBox;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleObjectProperty;

class Course {

    private SimpleStringProperty courseCode;
    private SimpleStringProperty courseName;
    private SimpleIntegerProperty maxCapacity;
    private ObservableList<Student> enrolledStudents;
    // New instance variable to store individual student grades
    private Map<Student, Integer> studentGrades = new HashMap<>();

    // Constructor
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = new SimpleStringProperty(courseCode);
        this.courseName = new SimpleStringProperty(courseName);
        this.maxCapacity = new SimpleIntegerProperty(maxCapacity);
        this.enrolledStudents = FXCollections.observableArrayList();
    }

    // Getter methods
    public String getCourseCode() {
        return courseCode.get();
    }

    public SimpleStringProperty courseCodeProperty() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName.get();
    }

    public SimpleStringProperty courseNameProperty() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity.get();
    }

    public SimpleIntegerProperty maxCapacityProperty() {
        return maxCapacity;
    }

    public ObservableList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public SimpleListProperty<Student> enrolledStudentsProperty() {
        return new SimpleListProperty<>(enrolledStudents);
    }

    // Method to enroll a student in the course
    public void enrollStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity.get()) {
            enrolledStudents.add(student);
        } else {
            showAlert("Enrollment Warning",
                    "Cannot enroll more students in " + courseName + ". Maximum capacity reached.");
        }
    }

    // Helper method to show alert dialogs
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Method to assign grades for a specific student
    public void assignGrade(Student student, int grade) {
        studentGrades.put(student, grade);
    }

    // Method to get the grade for a specific student
    public int getGrade(Student student) {
        return studentGrades.getOrDefault(student, 0);
    }

    // Override toString method to display the course code
    @Override
    public String toString() {
        return getCourseCode();
    }

}

class Student {
    private SimpleStringProperty name;
    private SimpleObjectProperty<Integer> studentID;
    private SimpleObjectProperty<ObservableList<Course>> enrolledCourses;
    private SimpleObjectProperty<ObservableList<Integer>> grades;

    // Constructor
    public Student(String name, int studentID) {
        this.name = new SimpleStringProperty(name);
        this.studentID = new SimpleObjectProperty<>(studentID);
        this.enrolledCourses = new SimpleObjectProperty<>(FXCollections.observableArrayList());
        this.grades = new SimpleObjectProperty<>(FXCollections.observableArrayList());
    }

    // Getter methods
    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public SimpleObjectProperty<Integer> getStudentID() {
        return studentID;
    }

    public SimpleObjectProperty<Integer> studentIDProperty() {
        return studentID;
    }

    public SimpleObjectProperty<ObservableList<Course>> enrolledCoursesProperty() {
        return enrolledCourses;
    }

    public SimpleObjectProperty<ObservableList<Integer>> gradesProperty() {
        return grades;
    }

    // Setter method for updating the student's name
    public void setName(String name) {
        this.name.set(name);
    }

    // Method to enroll in a course
    public void enrollInCourse(Course course) {
        ObservableList<Course> courses = enrolledCourses.get();

        if (courses.size() < 5) {
            if (!courses.contains(course)) {
                courses.add(course);
                course.enrollStudent(this);
                showAlert(AlertType.INFORMATION, "Enrollment Successful",
                        name + " enrolled in " + course.getCourseName());
            } else {
                showAlert(AlertType.WARNING, "Enrollment Warning",
                        name + " is already enrolled in " + course.getCourseName());
            }
        } else {
            showAlert(AlertType.WARNING, "Enrollment Warning",
                    name + " cannot enroll in more than 5 courses.");
        }
    }

    // Method to assign a grade for a course
    public void assignGrade(Course course, int grade) {
        ObservableList<Course> courses = enrolledCourses.get();

        if (courses.contains(course)) {
            // Assuming grade is an integer between 0 and 100
            if (grade >= 0 && grade <= 100) {
                // Assign the grade to the student for the specified course
                course.assignGrade(this, grade);
                showAlert(AlertType.INFORMATION, "Grade Assignment Successful",
                        name + "'s grade for " + course.getCourseName() + ": " + grade);

                // Update the overall grade for the student
                int totalGrades = calculateTotalGrades();
                int totalCourses = courses.size();
                int overallGrade = totalCourses > 0 ? totalGrades / totalCourses : 0;
                showAlert(AlertType.INFORMATION, "Overall Grade Update",
                        name + "'s overall grade: " + overallGrade);
            } else {
                showAlert(AlertType.ERROR, "Invalid Grade",
                        "Invalid grade. Please enter a grade between 0 and 100.");
            }
        } else {
            showAlert(AlertType.WARNING, "Grade Assignment Warning",
                    name + " is not enrolled in " + course.getCourseName());
        }
    }

    // Helper method to calculate the total grades for all courses
    private int calculateTotalGrades() {
        int totalGrades = 0;

        // Directly iterate over ObservableList
        for (Course course : enrolledCourses.get()) {
            totalGrades += course.getGrade(this);
        }

        return totalGrades;
    }

    // Helper method to show alert dialogs
    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}

class CourseManagement {
    private List<Course> courses = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getStudents() {
        return students;
    }

    private boolean isCourseCodeUnique(String courseCode) {
        // Check if any existing course has the same course code
        return courses.stream().noneMatch(course -> course.getCourseCode().equals(courseCode));
    }

    private boolean isStudentIdUnique(int studentId) {
        // Check if any existing student has the same ID
        return students.stream().noneMatch(student -> student.getStudentID().get() == studentId);
    }

    public void addCourse(String courseCode, String courseName, int maxCapacity) {
        // Check if a course with the same course code already exists
        if (isCourseCodeUnique(courseCode)) {
            // If unique, add the course
            Course newCourse = new Course(courseCode, courseName, maxCapacity);
            courses.add(newCourse);
        } else {
            // If not unique, display an error alert
            showAlert("Error", "Course with code '" + courseCode + "' already exists.");
        }
    }

    public void enrollStudent(Student student, Course course) {
        // Check if a student with the same ID already exists
        if (isStudentIdUnique(student.getStudentID().get())) {
            // If unique, enroll the student
            if (!students.contains(student)) {
                students.add(student);
            }

            if (course.getEnrolledStudents().size() < course.getMaxCapacity()) {
                student.enrollInCourse(course);
            } else {
                showAlert("Enrollment Warning",
                        "Cannot enroll more students in " + course.getCourseName() + ". Maximum capacity reached.");
            }
        } else {
            // If not unique, display an error alert
            showAlert("Error", "Student with ID '" + student.getStudentID() + "' already exists.");
        }
    }

    public void assignGrade(Student student, Course course, int grade) {
        if (students.contains(student)) {
            student.assignGrade(course, grade);
        } else {
            showAlert("Error", "Student not found in the system.");
        }
    }

    public void calculateOverallGrade(Student student) {
        int totalGrades = 0;

        // Use get() to access the ObservableList<Course> from the property
        int totalCourses = student.enrolledCoursesProperty().get().size();

        if (totalCourses > 0) {
            for (Course course : student.enrolledCoursesProperty().get()) {
                totalGrades += course.getGrade(student);
            }

            int overallGrade = totalGrades / totalCourses;
            showAlert("Overall Grade",
                    student.getName() + "'s overall grade: " + overallGrade);
        } else {
            showAlert("No Courses Enrolled",
                    student.getName() + " is not enrolled in any courses.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

// JavaFX application class
public class CourseManagementGUI extends Application {

    // Instance of CourseManagement class
    private CourseManagement courseManagement;

    // Main method
    public static void main(String[] args) {
        launch(args);
    }

    // Start method (JavaFX application entry point)
    @Override
    public void start(Stage primaryStage) {
        // Initialize CourseManagement instance
        courseManagement = new CourseManagement();

        // Set up the primary stage
        primaryStage.setTitle("Course Management System");

        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create a title label
        Label titleLabel = new Label("Course Management System");
        titleLabel.setStyle("-fx-font-size: 20pt;");
        grid.add(titleLabel, 0, 0, 2, 1);

        // Create buttons for various actions
        Button addCourseButton = new Button("Add New Course");
        addCourseButton.setOnAction(e -> showAddCourseDialog());
        grid.add(addCourseButton, 0, 1);

        Button enrollStudentButton = new Button("Enroll Student");
        enrollStudentButton.setOnAction(e -> showEnrollStudentDialog());
        grid.add(enrollStudentButton, 1, 1);

        Button assignGradeButton = new Button("Assign Grade");
        assignGradeButton.setOnAction(e -> showAssignGradeDialog());
        grid.add(assignGradeButton, 0, 2);

        Button calculateOverallGradeButton = new Button("Calculate Overall Grade");
        calculateOverallGradeButton.setOnAction(e -> showCalculateOverallGradeDialog());
        grid.add(calculateOverallGradeButton, 1, 2);

        Button viewStudentListButton = new Button("View Student List");
        viewStudentListButton.setOnAction(e -> showStudentListDialog());
        grid.add(viewStudentListButton, 0, 3);

        Button viewCourseListButton = new Button("View Course List");
        viewCourseListButton.setOnAction(e -> showCourseListDialog());
        grid.add(viewCourseListButton, 1, 3);

        // Create a scene
        Scene scene = new Scene(grid, 500, 250);
        primaryStage.setScene(scene);

        // Show the primary stage
        primaryStage.show();
    }

    // Method to show a dialog with a list of students
    private void showStudentListDialog() {
        // Create a new stage for the student list
        Stage studentListStage = new Stage();
        studentListStage.setTitle("Student List");

        // Create a table view for students
        TableView<Student> table = new TableView<>();
        table.setItems(FXCollections.observableList(courseManagement.getStudents()));

        // Define columns for the table
        TableColumn<Student, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

        TableColumn<Student, Integer> idCol = new TableColumn<>("Student ID");
        idCol.setCellValueFactory(cellData -> cellData.getValue().studentIDProperty());

        TableColumn<Student, ObservableList<Course>> enrolledCoursesCol = new TableColumn<>("Enrolled Courses");
        enrolledCoursesCol.setCellValueFactory(cellData -> cellData.getValue().enrolledCoursesProperty());

        TableColumn<Student, Void> actionCol = new TableColumn<>("Action");
        actionCol.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");

            {
                // Event handler for the update button
                updateButton.setOnAction(event -> {
                    Student student = getTableView().getItems().get(getIndex());
                    showUpdateStudentDialog(student);
                });
            }

            // Update cell item
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(updateButton);
                }
            }
        });

        // Add columns to the table
        table.getColumns().add(nameCol);
        table.getColumns().add(idCol);
        table.getColumns().add(enrolledCoursesCol);
        table.getColumns().add(actionCol);

        // Create a VBox for the table
        VBox vbox = new VBox(table);

        // Create a scene for the student list dialog
        Scene dialogScene = new Scene(vbox, 500, 400);
        studentListStage.setScene(dialogScene);

        // Show the student list stage
        studentListStage.show();
    }

    // Method to show a dialog for updating student information
    private void showUpdateStudentDialog(Student student) {
        // Create a new stage for updating student information
        Stage updateStudentStage = new Stage();
        updateStudentStage.setTitle("Update Student");

        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create labels and fields for name and ID
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField(student.getName());
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField(String.valueOf(student.getStudentID().get()));
        idField.setDisable(true); // Disable editing student ID
        grid.add(idLabel, 0, 1);
        grid.add(idField, 1, 1);

        // Add other fields as needed

        // Create an update button
        Button updateButton = new Button("Update");
        updateButton.setOnAction(event -> {
            // Perform the update
            student.setName(nameField.getText());
            // Update other fields as needed

            // Close the update student stage
            updateStudentStage.close();
        });

        // Add components to the grid
        grid.add(updateButton, 1, 2);

        // Create a scene for the update student dialog
        Scene dialogScene = new Scene(grid, 300, 200);
        updateStudentStage.setScene(dialogScene);

        // Show the update student stage
        updateStudentStage.show();
    }

    // Method to show a dialog with a list of courses
    private void showCourseListDialog() {
        // Create a new stage for the course list
        Stage courseListStage = new Stage();
        courseListStage.setTitle("Course List");

        // Create a table view for courses
        TableView<Course> table = new TableView<>();
        table.setItems(FXCollections.observableList(courseManagement.getCourses()));

        // Define columns for the table
        TableColumn<Course, String> codeCol = new TableColumn<>("Course Code");
        codeCol.setCellValueFactory(cellData -> cellData.getValue().courseCodeProperty());

        TableColumn<Course, String> nameCol = new TableColumn<>("Course Name");
        nameCol.setCellValueFactory(cellData -> cellData.getValue().courseNameProperty());

        TableColumn<Course, Integer> capacityCol = new TableColumn<>("Max Capacity");
        capacityCol.setCellValueFactory(cellData -> cellData.getValue().maxCapacityProperty().asObject());

        // Add columns to the table
        table.getColumns().add(codeCol);
        table.getColumns().add(nameCol);
        table.getColumns().add(capacityCol);

        // Create a VBox for the table
        VBox vbox = new VBox(table);

        // Create a scene for the course list dialog
        Scene dialogScene = new Scene(vbox, 500, 400);
        courseListStage.setScene(dialogScene);

        // Show the course list stage
        courseListStage.show();
    }

    // Method to show a dialog for adding a new course
    private void showAddCourseDialog() {
        // Create a new stage for adding a new course
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add New Course");

        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create labels and text fields for course code, name, and max capacity
        Label courseCodeLabel = new Label("Course Code:");
        TextField courseCodeField = new TextField();
        grid.add(courseCodeLabel, 0, 0);
        grid.add(courseCodeField, 1, 0);

        Label courseNameLabel = new Label("Course Name:");
        TextField courseNameField = new TextField();
        grid.add(courseNameLabel, 0, 1);
        grid.add(courseNameField, 1, 1);

        Label maxCapacityLabel = new Label("Max Capacity:");
        TextField maxCapacityField = new TextField();
        grid.add(maxCapacityLabel, 0, 2);
        grid.add(maxCapacityField, 1, 2);

        // Create an add button
        Button addButton = new Button("Add Course");
        addButton.setOnAction(e -> {
            // Get input values
            String courseCode = courseCodeField.getText();
            String courseName = courseNameField.getText();
            int maxCapacity = Integer.parseInt(maxCapacityField.getText());

            // Add a new course
            courseManagement.addCourse(courseCode, courseName, maxCapacity);

            // Close the add course dialog
            dialogStage.close();
        });
        grid.add(addButton, 1, 3);

        // Create a scene for the add course dialog
        Scene dialogScene = new Scene(grid, 300, 200);
        dialogStage.setScene(dialogScene);

        // Show the add course stage
        dialogStage.show();
    }

    // Method to show a dialog for enrolling a student
    private void showEnrollStudentDialog() {
        // Create a new stage for enrolling a student
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Enroll Student");

        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create labels and text fields for student name, ID, and course selection
        Label studentNameLabel = new Label("Student Name:");
        TextField studentNameField = new TextField();
        grid.add(studentNameLabel, 0, 0);
        grid.add(studentNameField, 1, 0);

        Label studentIdLabel = new Label("Student ID:");
        TextField studentIdField = new TextField();
        grid.add(studentIdLabel, 0, 1);
        grid.add(studentIdField, 1, 1);

        ComboBox<Course> coursesComboBox = new ComboBox<>();
        coursesComboBox.getItems().addAll(courseManagement.getCourses());
        coursesComboBox.setPromptText("Select Course");
        grid.add(coursesComboBox, 0, 2, 2, 1);

        // Create an enroll button
        Button enrollButton = new Button("Enroll");
        enrollButton.setOnAction(e -> {
            // Get input values
            String studentName = studentNameField.getText();
            int studentId = Integer.parseInt(studentIdField.getText());
            Course selectedCourse = coursesComboBox.getValue();

            // Validate input and enroll the student
            if (selectedCourse != null) {
                Student student = new Student(studentName, studentId);
                courseManagement.enrollStudent(student, selectedCourse);

                // Close the enroll student dialog
                dialogStage.close();
            } else {
                showAlert("Error", "Please select a course.");
            }
        });
        grid.add(enrollButton, 1, 3);

        // Create a scene for the enroll student dialog
        Scene dialogScene = new Scene(grid, 300, 200);
        dialogStage.setScene(dialogScene);

        // Show the enroll student stage
        dialogStage.show();
    }

    // Method to show a dialog for assigning a grade
    private void showAssignGradeDialog() {
        // Create a new stage for assigning a grade
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Assign Grade");

        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create labels and text fields for student ID, course selection, and grade
        Label studentIdLabel = new Label("Student ID:");
        TextField studentIdField = new TextField();
        grid.add(studentIdLabel, 0, 0);
        grid.add(studentIdField, 1, 0);

        ComboBox<Course> coursesComboBox = new ComboBox<>();
        coursesComboBox.getItems().addAll(courseManagement.getCourses());
        coursesComboBox.setPromptText("Select Course");
        grid.add(coursesComboBox, 0, 1, 2, 1);

        Label gradeLabel = new Label("Grade:");
        TextField gradeField = new TextField();
        grid.add(gradeLabel, 0, 2);
        grid.add(gradeField, 1, 2);

        Button assignButton = new Button("Assign Grade");
        assignButton.setOnAction(e -> {
            int studentId = Integer.parseInt(studentIdField.getText());
            Course selectedCourse = coursesComboBox.getValue();
            int grade = Integer.parseInt(gradeField.getText());
            Student student = findStudent(studentId);
            if (student != null && selectedCourse != null) {
                courseManagement.assignGrade(student, selectedCourse, grade);
                dialogStage.close();
            } else {
                showAlert("Error", "Student not found or course not selected.");
            }
        });
        grid.add(assignButton, 1, 3);

        Scene dialogScene = new Scene(grid, 300, 200);
        dialogStage.setScene(dialogScene);

        dialogStage.show();
    }

    // Method to show a dialog for calculating the overall grade
    private void showCalculateOverallGradeDialog() {
        // Create a new stage for calculating the overall grade
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Calculate Overall Grade");

        // Create a grid layout
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create labels and text fields for student ID
        Label studentIdLabel = new Label("Student ID:");
        TextField studentIdField = new TextField();
        grid.add(studentIdLabel, 0, 0);
        grid.add(studentIdField, 1, 0);

        // Create a button to trigger the overall grade calculation
        Button calculateButton = new Button("Calculate Overall Grade");
        calculateButton.setOnAction(e -> {
            int studentId = Integer.parseInt(studentIdField.getText());
            Student student = findStudent(studentId);
            if (student != null) {
                courseManagement.calculateOverallGrade(student);
                dialogStage.close();
            } else {
                showAlert("Error", "Student not found.");
            }
        });
        grid.add(calculateButton, 1, 1);

        Scene dialogScene = new Scene(grid, 300, 150);
        dialogStage.setScene(dialogScene);

        dialogStage.show();
    }

    // Method to find a student based on student ID
    private Student findStudent(int studentID) {
        for (Student student : courseManagement.getStudents()) {
            if (student.getStudentID().get() == studentID) {
                return student;
            }
        }
        return null;
    }

    // Method to show an alert dialog with a given title and content
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
