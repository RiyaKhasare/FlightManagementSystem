package service;

import model.Flight;
import java.util.List;
import java.util.Set;

public interface FlightService {
    Flight addFlight(Flight flight);
    void deleteFlight(long flightNumber);
    List<Flight> viewFlights();
    int updateFlight(long flightNumber, Flight newFlight);
    Flight searchFlightByNumber(long flightNumber);
    Set<Flight> viewFlightByLocation(String source, String destination);
    Flight getHighRateFlight();
    List<Flight> sortFlightByHighToLow();
}
