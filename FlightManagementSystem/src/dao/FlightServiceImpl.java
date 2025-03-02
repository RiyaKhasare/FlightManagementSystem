package dao;

import model.Flight;
import service.FlightService;
import utility.JdbcUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FlightServiceImpl implements FlightService {
    
    @Override
    public Flight addFlight(Flight flight) {
        String insertQuery = "INSERT INTO FlightInfo VALUES(?,?,?,?,?,?)";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(insertQuery)) {

            ps.setLong(1, flight.getFlightNumber());
            ps.setString(2, flight.getFlightName());
            ps.setString(3, flight.getSource());
            ps.setString(4, flight.getDestination());
            ps.setDate(5, new java.sql.Date(flight.getFlightDate().getTime()));
            ps.setDouble(6, flight.getFlightPrice());

            int status = ps.executeUpdate();
            return status > 0 ? flight : null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteFlight(long flightNumber) {
        String deleteQuery = "DELETE FROM FlightInfo WHERE flight_id=?";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(deleteQuery)) {

            ps.setLong(1, flightNumber);
            int status = ps.executeUpdate();
            System.out.println(status > 0 ? "Flight deleted successfully!" : "Flight not found!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Flight> viewFlights() {
        List<Flight> flights = new ArrayList<>();
        String selectQuery = "SELECT * FROM FlightInfo";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Flight flight = new Flight(
                    rs.getLong(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), new Date(rs.getDate(5).getTime()), rs.getDouble(6)
                );
                flights.add(flight);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public int updateFlight(long flightNumber, Flight newFlight) {
        String updateQuery = "UPDATE FlightInfo SET flight_name=?, source=?, destination=?, flight_date=?, flight_price=? WHERE flight_id=?";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(updateQuery)) {

            ps.setString(1, newFlight.getFlightName());
            ps.setString(2, newFlight.getSource());
            ps.setString(3, newFlight.getDestination());
            ps.setDate(4, new java.sql.Date(newFlight.getFlightDate().getTime()));
            ps.setDouble(5, newFlight.getFlightPrice());
            ps.setLong(6, flightNumber);

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Flight searchFlightByNumber(long flightNumber) {
        String searchQuery = "SELECT * FROM FlightInfo WHERE flight_id=?";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(searchQuery)) {

            ps.setLong(1, flightNumber);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Flight(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), new Date(rs.getDate(5).getTime()), rs.getDouble(6)
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<Flight> viewFlightByLocation(String source, String destination) {
        Set<Flight> flights = new HashSet<>();
        String locationQuery = "SELECT * FROM FlightInfo WHERE source=? AND destination=?";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(locationQuery)) {

            ps.setString(1, source);
            ps.setString(2, destination);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flights.add(new Flight(
                        rs.getLong(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), new Date(rs.getDate(5).getTime()), rs.getDouble(6)
                    ));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }


    @Override
    public Flight getHighRateFlight() {
        String highRateQuery = "SELECT * FROM FlightInfo ORDER BY flight_price DESC LIMIT 1";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(highRateQuery);
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return new Flight(
                    rs.getLong(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), new Date(rs.getDate(5).getTime()), rs.getDouble(6)
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Flight> sortFlightByHighToLow() {
        List<Flight> sortedFlights = new ArrayList<>();
        String sortQuery = "SELECT * FROM FlightInfo ORDER BY flight_price DESC";
        try (Connection con = JdbcUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sortQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                sortedFlights.add(new Flight(
                    rs.getLong(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), new Date(rs.getDate(5).getTime()), rs.getDouble(6)
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sortedFlights;
    }
}

