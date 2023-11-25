package A4_Flights;

/**
 * Cargo inherits from Flight
 * Cargo flights have crew, cargo, but no passengers
 */
public class CargoFlight extends Flight{

    // TODO-A2 - Complete this class, using javadocs as a reference
    private int cargoWeight;
    
    public CargoFlight(String flightNumber, String dayOfWeek, int departureTime, Location destination,  int numCrew, int cargoWeight){
        super(flightNumber, dayOfWeek, departureTime, destination, numCrew);
        this.cargoWeight = cargoWeight;
    }
    
    @Override
    public boolean checkWeight(){
        return (this.getNumCrew() * Common.AVERAGE_PERSON_WEIGHT) + cargoWeight <= Common.MAXIMUM_WEIGHT;
    }
    
    @Override
    public boolean checkCrew(){
        return this.getNumCrew() >= Common.MINIMUM_CREW;
    }   

    
    @Override
    public int calculateWeight(){
        return super.calculateWeight() + this.cargoWeight;
    }
    
    public double getCargoWeight(){
        return this.cargoWeight;
    }
    
    @Override
    public String getFlightType(){
        return "Cargo";
    }
    @Override
    public String toArchiveFormat() {
        return super.toArchiveFormat() + "," + this.cargoWeight;
    }
    @Override
    public String toDisplayReport(){
        return  super.toDisplayReport() + "\r\n\t" + "Cargo Weight: " + this.cargoWeight;
    }
} // end class CargoFlight
