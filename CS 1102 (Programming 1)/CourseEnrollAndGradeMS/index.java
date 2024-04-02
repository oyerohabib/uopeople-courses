import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private int studentID;
    private List<Course> enrolledCourses;
    private List<Integer> grades; // To store grades for each course

    public Student(String name, int studentID) {
        this.name = name;
        this.studentID = studentID;
        this.enrolledCourses = new ArrayList<>();
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentID() {
        return studentID;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public List<Integer> getGrades() {
        return grades;
    }

    public void enrollInCourse(Course course) {
        if (enrolledCourses.size() < 5) {
            if (!enrolledCourses.contains(course)) {
                enrolledCourses.add(course);
                course.enrollStudent(this);
                System.out.println(name + " enrolled in " + course.getCourseName());
            } else {
                System.out.println(name + " is already enrolled in " + course.getCourseName());
            }
        } else {
            System.out.println(name + " cannot enroll in more than 5 courses.");
        }
    }

    // Method to assign grades to students
    public void assignGrade(Course course, int grade) {
        if (enrolledCourses.contains(course)) {
            // Assuming grade is an integer between 0 and 100
            if (grade >= 0 && grade <= 100) {
                // Assign the grade to the student for the specified course
                course.assignGrade(this, grade);
                System.out.println(name + "'s grade for " + course.getCourseName() + ": " + grade);

                // Update the overall grade for the student
                int totalGrades = calculateTotalGrades();
                int totalCourses = enrolledCourses.size();
                int overallGrade = totalCourses > 0 ? totalGrades / totalCourses : 0;
                System.out.println(name + "'s overall grade: " + overallGrade);
            } else {
                System.out.println("Invalid grade. Please enter a grade between 0 and 100.");
            }
        } else {
            System.out.println(name + " is not enrolled in " + course.getCourseName());
        }
    }

    // Helper method to calculate the total grades for all enrolled courses
    private int calculateTotalGrades() {
        int totalGrades = 0;
        for (Course course : enrolledCourses) {
            totalGrades += course.getGrade(this);
        }
        return totalGrades;
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int maxCapacity;
    private List<Student> enrolledStudents; // To store enrolled students
    // New instance variable to store individual student grades
    private Map<Student, Integer> studentGrades = new HashMap<>();

    // New method to assign grades for a specific student
    public void assignGrade(Student student, int grade) {
        studentGrades.put(student, grade);
    }

    // New method to get the grade for a specific student
    public int getGrade(Student student) {
        return studentGrades.getOrDefault(student, 0);
    }

    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        if (enrolledStudents.size() < maxCapacity) {
            enrolledStudents.add(student);
        } else {
            System.out.println("Cannot enroll more students in " + courseName + ". Maximum capacity reached.");
        }
    }
}

class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();

    // Modify the addCourse method in CourseManagement class
    public static void addCourse(String courseCode, String courseName, int maxCapacity) {
        Course newCourse = new Course(courseCode, courseName, maxCapacity);
        courses.add(newCourse);
        System.out.println("Course added: " + newCourse.getCourseName());
    }

    // Modify the enrollStudent method in CourseManagement class
    public static void enrollStudent(Student student, Course course) {
        if (!students.contains(student)) {
            students.add(student);
        }

        if (course.getEnrolledStudents().size() < course.getMaxCapacity()) {
            student.enrollInCourse(course);
        } else {
            System.out.println(
                    "Cannot enroll more students in " + course.getCourseName() + ". Maximum capacity reached.");
        }
    }

    public static void assignGrade(Student student, Course course, int grade) {
        if (students.contains(student)) {
            student.assignGrade(course, grade);
        } else {
            System.out.println("Student not found in the system.");
        }
    }

    public static void calculateOverallGrade(Student student) {
        int totalGrades = 0;
        int totalCourses = student.getEnrolledCourses().size();

        if (totalCourses > 0) {
            for (Course course : student.getEnrolledCourses()) {
                totalGrades += course.getGrade(student);
            }

            int overallGrade = totalGrades / totalCourses;
            System.out.println(student.getName() + "'s overall grade: " + overallGrade);
        } else {
            System.out.println(student.getName() + " is not enrolled in any courses.");
        }
    }

    public static List<Course> getCourses() {
        return courses;
    }

    public static List<Student> getStudents() {
        return students;
    }
}

class AdministratorInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> courses = CourseManagement.getCourses();
        List<Student> students = CourseManagement.getStudents();

        while (true) {
            try {
                System.out.println("\n===== Administrator Interface =====");
                System.out.println("1. Add new course");
                System.out.println("2. Enroll student");
                System.out.println("3. Assign grade");
                System.out.println("4. Calculate overall course grade");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        String courseCode;
                        while (true) {
                            System.out.print("Enter course code (must start with letters and can contain numbers): ");
                            String input = scanner.nextLine();
                            if (input.matches("[a-zA-Z]+\\d*") && !input.isEmpty()) {
                                courseCode = input;
                                break;
                            } else {
                                System.out.println(
                                        "Invalid input. Please enter a non-empty value starting with letters and can contain numbers.");
                            }
                        }

                        String courseName;
                        while (true) {
                            System.out.print("Enter course name (can contain letters and numbers): ");
                            String input = scanner.nextLine();
                            if (input.matches("[a-zA-Z0-9 ]*") && !input.isEmpty() && !input.matches("\\d+")) {
                                courseName = input;
                                break;
                            } else {
                                System.out.println(
                                        "Invalid input. Please enter a non-empty value containing letters and numbers (but not numbers only).");
                            }
                        }

                        System.out.print("Enter max capacity: ");
                        int maxCapacity = scanner.nextInt();
                        CourseManagement.addCourse(courseCode, courseName, maxCapacity);
                        break;

                    case 2:
                        if (!courses.isEmpty()) {
                            System.out.print("Enter student name: ");
                            String studentName = scanner.nextLine();
                            System.out.print("Enter student ID: ");
                            int studentID = scanner.nextInt();

                            System.out.println("Available courses for enrollment:");
                            for (int i = 0; i < courses.size(); i++) {
                                System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
                            }

                            System.out.print("Enter the number of the course to enroll in: ");
                            int selectedCourseIndex = scanner.nextInt();

                            if (selectedCourseIndex > 0 && selectedCourseIndex <= courses.size()) {
                                Student student = new Student(studentName, studentID);
                                Course selectedCourse = courses.get(selectedCourseIndex - 1);
                                CourseManagement.enrollStudent(student, selectedCourse);
                                students.add(student);
                                System.out.println(studentName + " enrolled in " + selectedCourse.getCourseName());
                            } else {
                                System.out.println("Invalid course selection.");
                            }
                        } else {
                            System.out.println("No courses in the system. Please add a course first.");
                        }
                        break;

                    case 3:
                        if (!students.isEmpty()) {
                            System.out.print("Enter student ID: ");
                            int searchID = scanner.nextInt();
                            Student foundStudent = findStudent(searchID, students);

                            if (foundStudent != null) {
                                CourseManagement.assignGrade(foundStudent, selectCourse(scanner, courses),
                                        enterGrade(scanner));
                            } else {
                                System.out.println("Student not found.");
                            }
                        } else {
                            System.out.println("No students in the system.");
                        }
                        break;
                    case 4:
                        if (!students.isEmpty()) {
                            System.out.print("Enter student ID: ");
                            int searchID = scanner.nextInt();
                            Student foundStudent = findStudent(searchID, students);

                            if (foundStudent != null) {
                                CourseManagement.calculateOverallGrade(foundStudent);
                            } else {
                                System.out.println("Student not found.");
                            }

                        } else {
                            System.out.println("No students in the system.");
                        }
                        break;
                    case 0:
                        System.out.println("Exiting program. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid option.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    private static Student findStudent(int studentID, List<Student> students) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private static Course selectCourse(Scanner scanner, List<Course> courses) {
        if (!courses.isEmpty()) {
            System.out.println("Available courses for enrollment:");
            for (int i = 0; i < courses.size(); i++) {
                System.out.println((i + 1) + ". " + courses.get(i).getCourseName());
            }
            System.out.print("Enter the number of the course to assign a grade to: ");
            int courseNumber = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            if (courseNumber > 0 && courseNumber <= courses.size()) {
                return courses.get(courseNumber - 1);
            } else {
                System.out.println("Invalid course selection.");
                return null;
            }
        } else {
            System.out.println("No courses in the system. Please add a course first.");
            return null;
        }
    }

    private static int enterGrade(Scanner scanner) {
        System.out.print("Enter the grade: ");
        return scanner.nextInt();
    }
}
