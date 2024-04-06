import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int id;
    private int age;
    private double grade;

    // Constructor
    public Student(String name, int id, int age, double grade) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.grade = grade;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Grade: " + grade;
    }
}

class StudentManagement {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static int totalStudents = 0;

    // Add a new student to the list
    public static void addStudent(Student student) {
        studentList.add(student);
        totalStudents++;
    }

    // Update student information based on ID
    public static void updateStudent(int id, int newAge, double newGrade) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                student.setAge(newAge);
                student.setGrade(newGrade);
                System.out.println("Student information updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    // Retrieve and display student details based on ID
    public static void viewStudentDetails(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) {
                System.out.println(student.toString());
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    // Get the total number of students
    public static int getTotalStudents() {
        return totalStudents;
    }

    // Public method to access studentList
    public static ArrayList<Student> getStudentList() {
        return studentList;
    }
}

class StudentRecordManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Record Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Update student information");
            System.out.println("3. View student details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        addNewStudent(scanner);
                        break;
                    case 2:
                        updateStudentInformation(scanner);
                        break;
                    case 3:
                        viewStudentDetails(scanner);
                        break;
                    case 4:
                        System.out.println("Exiting Student Record Management System. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                // Consume the invalid input to avoid an infinite loop
                scanner.nextLine();
            }
        }
    }

    private static void addNewStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();

        // Check if the entered ID is already in use
        if (isIdTaken(id)) {
            System.out.println("Error: Student with ID " + id + " already exists. Please choose a different ID.");
            return;
        }

        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        System.out.print("Enter student grade: ");
        double grade = scanner.nextDouble();

        Student newStudent = new Student(name, id, age, grade);
        StudentManagement.addStudent(newStudent);

        System.out.println("Student added successfully.");
    }

    // Helper method to check if the ID is already taken
    private static boolean isIdTaken(int id) {
        for (Student student : StudentManagement.getStudentList()) {
            if (student.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private static void updateStudentInformation(Scanner scanner) {
        System.out.print("Enter student ID to update information: ");
        int id = scanner.nextInt();

        System.out.print("Enter new age: ");
        int newAge = scanner.nextInt();
        System.out.print("Enter new grade: ");
        double newGrade = scanner.nextDouble();

        StudentManagement.updateStudent(id, newAge, newGrade);
    }

    private static void viewStudentDetails(Scanner scanner) {
        System.out.print("Enter student ID to view details: ");
        int id = scanner.nextInt();
        StudentManagement.viewStudentDetails(id);
    }
}
