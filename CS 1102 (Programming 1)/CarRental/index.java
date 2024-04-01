import java.util.Scanner;

// Define the Vehicle interface
interface Vehicle {
    void setMake(String make);

    void setModel(String model);

    void setYear(int year);

    String getMake();

    String getModel();

    int getYear();
}

// Define the Car interface extending Vehicle
interface CarVehicle extends Vehicle {
    void setNumberOfDoors(int doors);

    void setFuelType(String fuelType);

    int getNumberOfDoors();

    String getFuelType();
}

// Define the Motorcycle interface extending Vehicle
interface MotorVehicle extends Vehicle {
    void setNumberOfWheels(int wheels);

    void setMotorcycleType(String type);

    int getNumberOfWheels();

    String getMotorcycleType();
}

// Define the Truck interface extending Vehicle
interface TruckVehicle extends Vehicle {
    void setCargoCapacity(double capacity);

    void setTransmissionType(String transmissionType);

    double getCargoCapacity();

    String getTransmissionType();
}

// Implement the interfaces in concrete classes
class Car implements CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numberOfDoors;
    private String fuelType;

    @Override
    public void setMake(String make) {
        if (isValidStringInput(make)) {
            this.make = make;
        } else {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string for the make.");
        }
    }

    @Override
    public void setModel(String model) {
        if (isValidStringInput(model)) {
            this.model = model;
        } else {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string for the model.");
        }
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setNumberOfDoors(int doors) {
        if (doors > 0) {
            this.numberOfDoors = doors;
        } else {
            throw new IllegalArgumentException("Number of doors must be a positive integer.");
        }
    }

    @Override
    public void setFuelType(String fuelType) {
        // Validate fuel type (assuming only petrol, diesel, or electric are valid)
        if ("petrol".equalsIgnoreCase(fuelType) || "diesel".equalsIgnoreCase(fuelType)
                || "electric".equalsIgnoreCase(fuelType)) {
            this.fuelType = fuelType;
        } else {
            throw new IllegalArgumentException("Invalid fuel type. Please enter petrol, diesel, or electric.");
        }
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    private boolean isValidStringInput(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    @Override
    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }
}

class Motorcycle implements MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numberOfWheels;
    private String motorcycleType;

    @Override
    public void setMake(String make) {
        if (isValidStringInput(make)) {
            this.make = make;
        } else {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string for the make.");
        }
    }

    @Override
    public void setModel(String model) {
        if (isValidStringInput(model)) {
            this.model = model;
        } else {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string for the model.");
        }
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setNumberOfWheels(int wheels) {
        if (wheels > 0) {
            this.numberOfWheels = wheels;
        } else {
            throw new IllegalArgumentException("Number of wheels must be a positive integer.");
        }
    }

    @Override
    public void setMotorcycleType(String type) {
        // Validate motorcycle type (assuming only sport, cruiser, or off-road are
        // valid)
        if ("sport".equalsIgnoreCase(type) || "cruiser".equalsIgnoreCase(type) || "off-road".equalsIgnoreCase(type)) {
            this.motorcycleType = type;
        } else {
            throw new IllegalArgumentException("Invalid motorcycle type. Please enter sport, cruiser, or off-road.");
        }
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    private boolean isValidStringInput(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    @Override
    public int getNumberOfWheels() {
        return numberOfWheels;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }
}

class Truck implements TruckVehicle {
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

    @Override
    public void setMake(String make) {
        if (isValidStringInput(make)) {
            this.make = make;
        } else {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string for the make.");
        }
    }

    @Override
    public void setModel(String model) {
        if (isValidStringInput(model)) {
            this.model = model;
        } else {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string for the model.");
        }
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void setCargoCapacity(double capacity) {
        if (capacity >= 0) {
            this.cargoCapacity = capacity;
        } else {
            throw new IllegalArgumentException("Cargo capacity must be a non-negative value.");
        }
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        // Validate transmission type (assuming only manual or automatic are valid)
        if ("manual".equalsIgnoreCase(transmissionType) || "automatic".equalsIgnoreCase(transmissionType)) {
            this.transmissionType = transmissionType;
        } else {
            throw new IllegalArgumentException("Invalid transmission type. Please enter manual or automatic.");
        }
    }

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    private boolean isValidStringInput(String input) {
        return input.matches("^[a-zA-Z]+$");
    }

    @Override
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }
}

class VehicleInformationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Interactive program to create and display details of different vehicle
        // objects.
        System.out.println("Welcome to the Vehicle Information System!");

        // Create a Car
        Car car = new Car();
        System.out.println("\nEnter details for a car:");
        enterVehicleDetails(car, scanner);
        enterCarDetails(car, scanner);
        displayVehicleDetails(car);

        // Create a Motorcycle
        Motorcycle motorcycle = new Motorcycle();
        System.out.println("\nEnter details for a motorcycle:");
        enterVehicleDetails(motorcycle, scanner);
        enterMotorcycleDetails(motorcycle, scanner);
        displayVehicleDetails(motorcycle);

        // Create a Truck
        Truck truck = new Truck();
        System.out.println("\nEnter details for a truck:");
        enterVehicleDetails(truck, scanner);
        enterTruckDetails(truck, scanner);
        displayVehicleDetails(truck);

        scanner.close();
    }

    // Helper method to enter common vehicle details
    private static void enterVehicleDetails(Vehicle vehicle, Scanner scanner) {
        try {
            System.out.print("Enter make: ");
            String make = scanner.next();
            validateStringInput(make); // Validate make input
            vehicle.setMake(make);

            System.out.print("Enter model: ");
            String model = scanner.next();
            validateStringInput(make); // Validate make input
            vehicle.setModel(model);

            System.out.print("Enter year: ");
            int year = scanner.nextInt();
            vehicle.setYear(year);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.nextLine(); // Consume the invalid input
            enterVehicleDetails(vehicle, scanner); // Retry entering vehicle details
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); // Consume the invalid input
            enterVehicleDetails(vehicle, scanner); // Retry entering vehicle details
        }
    }

    // Helper method to validate string input
    private static void validateStringInput(String input) {
        if (!input.matches("^[a-zA-Z]+$")) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid string.");
        }
    }

    // Helper method to enter car-specific details
    private static void enterCarDetails(Car car, Scanner scanner) {
        try {
            System.out.print("Enter number of doors: ");
            car.setNumberOfDoors(scanner.nextInt());

            System.out.print("Enter fuel type (petrol, diesel, or electric): ");
            String fuelType = scanner.next();
            validateFuelType(fuelType); // Validate fuel type input
            car.setFuelType(fuelType);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.nextLine(); // Consume the invalid input
            enterCarDetails(car, scanner); // Retry entering car details
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); // Consume the invalid input
            enterCarDetails(car, scanner); // Retry entering car details
        }
    }

    // Helper method to validate fuel type input
    private static void validateFuelType(String input) {
        if (!input.matches("^(petrol|diesel|electric)$")) {
            throw new IllegalArgumentException("Invalid fuel type. Please enter petrol, diesel, or electric.");
        }
    }

    // Helper method to enter motorcycle-specific details
    private static void enterMotorcycleDetails(Motorcycle motorcycle, Scanner scanner) {
        try {
            System.out.print("Enter number of wheels: ");
            motorcycle.setNumberOfWheels(scanner.nextInt());

            System.out.print("Enter motorcycle type (sport, cruiser, or off-road): ");
            String type = scanner.next();
            validateMotorcycleType(type); // Validate motorcycle type input
            motorcycle.setMotorcycleType(type);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.nextLine(); // Consume the invalid input
            enterMotorcycleDetails(motorcycle, scanner); // Retry entering motorcycle details
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); // Consume the invalid input
            enterMotorcycleDetails(motorcycle, scanner); // Retry entering motorcycle details
        }
    }

    // Helper method to validate motorcycle type input
    private static void validateMotorcycleType(String input) {
        if (!input.matches("^(sport|cruiser|off-road)$")) {
            throw new IllegalArgumentException("Invalid motorcycle type. Please enter sport, cruiser, or off-road.");
        }
    }

    // Helper method to enter truck-specific details
    private static void enterTruckDetails(Truck truck, Scanner scanner) {
        try {
            System.out.print("Enter cargo capacity (in tons): ");
            truck.setCargoCapacity(scanner.nextDouble());

            System.out.print("Enter transmission type (manual or automatic): ");
            String transmissionType = scanner.next();
            validateTransmissionType(transmissionType); // Validate transmission type input
            truck.setTransmissionType(transmissionType);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.nextLine(); // Consume the invalid input
            enterTruckDetails(truck, scanner); // Retry entering truck details
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            scanner.nextLine(); // Consume the invalid input
            enterTruckDetails(truck, scanner); // Retry entering truck details
        }
    }

    // Helper method to validate transmission type input
    private static void validateTransmissionType(String input) {
        if (!input.matches("^(manual|automatic)$")) {
            throw new IllegalArgumentException("Invalid transmission type. Please enter manual or automatic.");
        }
    }

    // Helper method to display all vehicle details
    private static void displayVehicleDetails(Vehicle vehicle) {
        System.out.println("\nVehicle Details:");
        System.out.println("Make: " + vehicle.getMake());
        System.out.println("Model: " + vehicle.getModel());
        System.out.println("Year: " + vehicle.getYear());

        if (vehicle instanceof CarVehicle) {
            CarVehicle car = (CarVehicle) vehicle;
            System.out.println("Number of Doors: " + car.getNumberOfDoors());
            System.out.println("Fuel Type: " + car.getFuelType());
        } else if (vehicle instanceof MotorVehicle) {
            MotorVehicle motorcycle = (MotorVehicle) vehicle;
            System.out.println("Number of Wheels: " + motorcycle.getNumberOfWheels());
            System.out.println("Motorcycle Type: " + motorcycle.getMotorcycleType());
        } else if (vehicle instanceof TruckVehicle) {
            TruckVehicle truck = (TruckVehicle) vehicle;
            System.out.println("Cargo Capacity: " + truck.getCargoCapacity() + " tons");
            System.out.println("Transmission Type: " + truck.getTransmissionType());
        }
    }

}
