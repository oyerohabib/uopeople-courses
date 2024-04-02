import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

// Employee class represents the data structure for an employee in a company
class Employee {
    // Private fields to store employee attributes
    private String name;
    private int age;
    private String department;
    private double salary;

    // Constructor to initialize an Employee object with the provided attributes
    public Employee(String name, int age, String department, double salary) {
        this.name = name;
        this.age = age;
        this.department = department;
        this.salary = salary;
    }

    // Getter methods to retrieve individual attributes of the employee

    // Getter for the name attribute
    public String getName() {
        return name;
    }

    // Getter for the age attribute
    public int getAge() {
        return age;
    }

    // Getter for the department attribute
    public String getDepartment() {
        return department;
    }

    // Getter for the salary attribute
    public double getSalary() {
        return salary;
    }

    // Override toString() method to provide a custom string representation of the
    // employee
    @Override
    public String toString() {
        return "Employee{name='" + name + "', age=" + age + ", department='" + department + "', salary=" + salary + '}';
    }
}

class EmployeeProcessing {

    public static void main(String[] args) {
        // Step 1: Create and populate the dataset
        List<Employee> employees = Arrays.asList(
                new Employee("Thomas", 30, "HR", 60000),
                new Employee("John", 25, "IT", 50000),
                new Employee("Alice", 30, "HR", 60000),
                new Employee("Grace", 40, "Finance", 80000),
                new Employee("Michael", 30, "HR", 50000),
                new Employee("Daniel", 25, "IT", 60000),
                new Employee("Emma", 25, "Marketing", 50000),
                new Employee("Ava", 40, "Finance", 60000),
                new Employee("Henry", 25, "HR", 50000),
                new Employee("Jack", 30, "IT", 60000));

        // Step 2: Define a Function interface for concatenating name and department
        Function<Employee, String> concatenateNameAndDept = e -> e.getName() + " - " + e.getDepartment();

        // Step 3: Use streams to generate a new collection of concatenated strings
        List<String> concatenatedList = employees.stream()
                .map(concatenateNameAndDept)
                .collect(Collectors.toList());

        System.out.println("Concatenated Strings: " + concatenatedList);
        System.out.println();

        // Step 4: Calculate the average salary using stream's built-in functions
        double averageSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);

        System.out.println("Average Salary: " + averageSalary);
        System.out.println();

        // Step 5: Generalize the program with a filter function for employees above a
        // certain age
        int ageThreshold = 30;
        List<String> filteredConcatenatedList = employees.stream()
                .filter(e -> e.getAge() > ageThreshold)
                .map(concatenateNameAndDept)
                .collect(Collectors.toList());

        System.out.println("Filtered Concatenated Strings (Age > " + ageThreshold + "): " + filteredConcatenatedList);
        System.out.println();

        // Sorting employees by salary in descending order
        List<Employee> sortedBySalaryDescending = employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .collect(Collectors.toList());
        System.out.println("Employees sorted by salary (descending): " + sortedBySalaryDescending);
        System.out.println();

        // Filtering employees by department
        String targetDepartment = "HR";
        List<Employee> filteredByDepartment = employees.stream()
                .filter(e -> e.getDepartment().equals(targetDepartment))
                .collect(Collectors.toList());
        System.out.println("Employees in the " + targetDepartment + " department: " + filteredByDepartment);
        System.out.println();

        // Calculating total salary expenditure
        double totalSalaryExpenditure = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Total Salary Expenditure: " + totalSalaryExpenditure);
        System.out.println();

        // Grouping employees by department
        Map<String, List<Employee>> employeesByDepartment = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Employees grouped by department: " + employeesByDepartment);
        System.out.println();

        // Displaying employee statistics
        DoubleSummaryStatistics salaryStatistics = employees.stream()
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();
        IntSummaryStatistics ageStatistics = employees.stream()
                .mapToInt(Employee::getAge)
                .summaryStatistics();
        System.out.println("Salary Statistics: " + salaryStatistics);
        System.out.println();
        System.out.println("Age Statistics: " + ageStatistics);
        System.out.println();
    }
}
