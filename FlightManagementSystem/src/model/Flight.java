package model;

import java.util.Date;

public class Flight {
	

	
	    private long flightNumber;
	    private String flightName;
	    private String source;
	    private String destination;
	    private Date flightDate;
	    private double flightPrice;

	    // Constructor
	    public Flight(long flightNumber, String flightName, String source, String destination, Date flightDate, double flightPrice) {
	        this.flightNumber = flightNumber;
	        this.flightName = flightName;
	        this.source = source;
	        this.destination = destination;
	        this.flightDate = flightDate;
	        this.flightPrice = flightPrice;
	    }

	    // Default Constructor
	    public Flight() {}

	    // Getters and Setters
	    public long getFlightNumber() { return flightNumber; }
	    public void setFlightNumber(long flightNumber) { this.flightNumber = flightNumber; }

	    public String getFlightName() { return flightName; }
	    public void setFlightName(String flightName) { this.flightName = flightName; }

	    public String getSource() { return source; }
	    public void setSource(String source) { this.source = source; }

	    public String getDestination() { return destination; }
	    public void setDestination(String destination) { this.destination = destination; }

	    public Date getFlightDate() { return flightDate; }
	    public void setFlightDate(Date flightDate) { this.flightDate = flightDate; }

	    public double getFlightPrice() { return flightPrice; }
	    public void setFlightPrice(double flightPrice) { this.flightPrice = flightPrice; }

	    @Override
	    public String toString() {
	        return "Flight [Flight Number=" + flightNumber + ", Name=" + flightName + 
	               ", Source=" + source + ", Destination=" + destination + 
	               ", Date=" + flightDate + ", Price=" + flightPrice + "]";
	    }
	}



