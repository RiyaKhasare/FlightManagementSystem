package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import dao.FlightServiceImpl;
import model.Flight;
import service.FlightService;

public class FlightDriver {
	public static void main(String[] args) throws Exception {
		FlightService flightService = new FlightServiceImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Flight Management System =====");
            System.out.println("1. Add Flight");
            System.out.println("2. View Flights");
            System.out.println("3. Delete Flight");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Flight Number: ");
                    long flightNumber = sc.nextLong();
                    sc.nextLine(); // Consume newline

                    System.out.print("Enter Flight Name: ");
                    String flightName = sc.nextLine();

                    System.out.print("Enter Source: ");
                    String source = sc.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();

                    System.out.print("Enter Flight Date (YYYY-MM-DD): ");
                    String flightDate = sc.nextLine();
                    java.sql.Date sqlDate = java.sql.Date.valueOf(flightDate); 

                    System.out.print("Enter Flight Price: ");
                    double flightPrice =sc.nextDouble();

                    Flight newFlight = new Flight(flightNumber, flightName, source, destination, sqlDate, flightPrice);
                    Flight addedFlight = flightService.addFlight(newFlight);
                    
                    if (addedFlight != null) {
                        System.out.println("\n✅ Flight added successfully!");
                    } else {
                        System.out.println("\n❌ Error adding flight.");
                    }
                    break;

                case 2:
                    List<Flight> flights = flightService.viewFlights();
                    if (flights.isEmpty()) {
                        System.out.println("\n❌ No flights found.");
                    } else {
                        System.out.println("\n===== Flight List =====");
                        for (Flight flight : flights) {
                            System.out.println(flight);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Flight Number to Delete: ");
                    long deleteFlightNumber = sc.nextLong();
                    flightService.deleteFlight(deleteFlightNumber);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("❌ Invalid choice! Please try again.");
            }
        }
    }

}
