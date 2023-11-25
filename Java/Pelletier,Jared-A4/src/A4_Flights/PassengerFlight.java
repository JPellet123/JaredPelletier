package A4_Flights;

/**
 * A passenger flight has no cargo
 */
public class PassengerFlight extends Flight{

    // TODO-A3 - Complete this class, using javadocs as a reference
    private int numPassengers;
    
    public PassengerFlight(String flightNumber, String dayOfWeek, int departureTime, Location destination,  int numCrew, int numPassengers){
        super(flightNumber, dayOfWeek, departureTime, destination, numCrew);
        this.numPassengers = numPassengers;
    }
    
    @Override
    public boolean checkCrew(){
        return this.getNumCrew() >= Common.MINIMUM_CREW;
    }
        
    @Override 
    public boolean checkPassengers(){
        return this.numPassengers >= Common.MINIMUM_PASSENGERS;
    }
    
    @Override
    public boolean checkWeight(){
        return (this.getNumCrew() + numPassengers) * Common.AVERAGE_PERSON_WEIGHT <= Common.MAXIMUM_WEIGHT;
    }
    
    @Override
    public int calculateWeight(){
        int total = numPassengers * Common.AVERAGE_PERSON_WEIGHT;
        return super.calculateWeight() + total;
    }
    
    @Override
    public String getFlightType(){
        return "Passenger";
    }
    
    public int getNumPassengers(){
        return this.numPassengers;    
    }
    @Override
    public String toArchiveFormat() {
        return super.toArchiveFormat() + "," + this.numPassengers;
    }
    @Override
    public String toDisplayReport(){
        return  super.toDisplayReport() + "\r\n\t" + "Number of Passengers: " + this.numPassengers;
    }
} // end class PassengerFlight
