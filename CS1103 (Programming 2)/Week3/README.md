# Clock Application

## Overview

The Clock application is a simple Java program that continuously displays the current time and date. It utilizes Java threads to update the time in the background and print it to the console. Thread priorities are used to ensure better timekeeping precision.

## Features

- Displays current time and date in the format "HH:mm:ss dd-MM-yyyy".
- Continuously updates the time in the background.
- Prints the time to the console.
- Utilizes Java threads for concurrency.
- Sets thread priorities for improved timekeeping.

## Requirements

- Java Development Kit (JDK) installed on your system.
- Basic understanding of Java programming and threading concepts.

## Usage

1. Clone the repository to your local machine.
2. Compile the Clock.java file: `javac Clock.java`
3. Run the compiled class file: `java Clock`
4. The application will continuously display the current time and date in the console.

## Documentation

### Clock Class

- `displayTime()`: Method to continuously update and print the current time.
- `updateCurrentTime()`: Method to update the current time and date.
- `printCurrentTime()`: Method to print the current time to the console.
- `main()`: The main method to start the application.

### Thread Implementation

- Two separate threads are used: one for updating the time and one for printing it to the console.
- Thread priorities are set to ensure timely console output.

## Contributing

Contributions are welcome! If you have suggestions, bug reports, or feature requests, please open an issue or submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
