# Vehicle Information System

This Java program implements a simple Vehicle Information System that allows users to input details for different types of vehicles, namely Cars, Motorcycles, and Trucks. The program defines interfaces for each vehicle type, extending a common Vehicle interface. It then provides concrete implementations for each vehicle type (Car, Motorcycle, and Truck) along with a main class, `VehicleInformationSystem`, to interactively create and display vehicle details.

## Class Structure

The program is structured as follows:

- Interfaces:

  - Vehicle
  - CarVehicle (extends Vehicle)
  - MotorVehicle (extends Vehicle)
  - TruckVehicle (extends Vehicle)

- Concrete Classes:

  - Car (implements CarVehicle)
  - Motorcycle (implements MotorVehicle)
  - Truck (implements TruckVehicle)

- Main Class:
  - VehicleInformationSystem

## Interfaces

### Vehicle

The base interface that defines methods for setting and getting common vehicle details such as make, model, and year.

### CarVehicle

Extends the Vehicle interface and includes methods for setting the number of doors and fuel type.

### MotorVehicle

Extends the Vehicle interface and includes methods for setting the number of wheels and motorcycle type.

### TruckVehicle

Extends the Vehicle interface and includes methods for setting the cargo capacity and transmission type.

## Classes

### Car

Implements the CarVehicle interface, providing methods to set and get details specific to cars.

### Motorcycle

Implements the MotorVehicle interface, providing methods to set and get details specific to motorcycles.

### Truck

Implements the TruckVehicle interface, providing methods to set and get details specific to trucks.

## Main Class: VehicleInformationSystem

The main class contains the `main` method, which initiates an interactive program to create instances of Car, Motorcycle, and Truck, and display their details.

## Helper Methods

### enterVehicleDetails

Helper method to enter common vehicle details such as make, model, and year. Validates user input for each detail.

### enterCarDetails

Helper method to enter car-specific details such as the number of doors and fuel type. Validates user input for each detail.

### enterMotorcycleDetails

Helper method to enter motorcycle-specific details such as the number of wheels and motorcycle type. Validates user input for each detail.

### enterTruckDetails

Helper method to enter truck-specific details such as cargo capacity and transmission type. Validates user input for each detail.

### displayVehicleDetails

Helper method to display common vehicle details such as make, model, and year.

## Input Validation

The program includes input validation for string inputs and specific values like fuel type, motorcycle type, and transmission type.

## Sample Usage

1. Welcome to the Vehicle Information System!
2. Enter details for a car:
   - Enter make: Honda
   - Enter model: Civic
   - Enter year: 2022
   - Enter number of doors: 4
   - Enter fuel type (petrol, diesel, or electric): petrol
3. Display car details.
4. Repeat steps for a motorcycle and a truck.
