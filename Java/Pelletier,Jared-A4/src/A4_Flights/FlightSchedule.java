package A4_Flights;

import java.util.ArrayList;

public class FlightSchedule {

    private final Flight[] flights;

    /**
     * Creates a new schedule based on the specified flights.
     *
     * @param flights the flights to be contained in this schedule
     */
    public FlightSchedule(Flight[] flights) {
        this.flights = flights;
    }

    /**
     * Gets all of the flights in this schedule.
     *
     * @return all of the flights in this schedule
     */
    public Flight[] getAllFlights() {
        return flights;
    }

    public Flight[] getFlightsByDestination(String locationCode) {
        // TODO-B1
        ArrayList<Flight> result = new ArrayList<>();
        for(Flight f : flights){
            if(f.getDestination().getLocationCode().equals(locationCode)){
                result.add(f);
            }
        }
        Flight[] newFlights = result.toArray(Flight[]::new);
        return newFlights;
    }

    private int countFlightsByDestination(String locationCode) {
        // TODO-B1
//        int count = 0;
//        for(Flight f : flights){
//            if(f.getDestination().getLocationCode().equals(locationCode)){
//                count++;                
//            }
//        }
        return -1;
    }

    public Flight[] getFlightsByType(String flightType) {
        // TODO-B2
        ArrayList<Flight> result = new ArrayList<>();
        for(Flight f : flights){
            if(f.getFlightType().equals(flightType)){
                result.add(f);
            }
        }
        Flight[] newFlights = result.toArray(Flight[]::new);
        return newFlights;
    }

    private int countFlightsByType(String flightType) {
        // TODO-B2
        return -1;
    }

} // end class FlightSchedule
