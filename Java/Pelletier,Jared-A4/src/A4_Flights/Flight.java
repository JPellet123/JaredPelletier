package A4_Flights;

/**
 * Flight - a nonstop flight departing from the home airport
 * Flight is the parent class for Training, Cargo, and Passenger Flights
 */
    public abstract class Flight extends java.lang.Object implements PolicyRules {

    // TODO-A1 - Complete this class, using javadocs as a reference
    
    private String dayOfWeek;
    private int departureTime;
    private Location destination;
    private String flightNumber;
    private int numCrew;
    
    
    public Flight(String flightNumber, String dayOfWeek, int departureTime, Location destination,  int numCrew){
        this.dayOfWeek = dayOfWeek;
        this.departureTime = departureTime;
        this.destination = destination;
        this.flightNumber = flightNumber;
        this.numCrew = numCrew;
                
    }
    
    public static Flight parseCSV(String line, Location.LocationFinder finder){
                try {
            String parts[] = line.split(",");

            if (parts.length < 6 || parts.length > 7)
                return null;
            
            String flightType = parts[0];            
            String flightNumber = parts[1];            
            String dayOfWeek = parts[2];            
            int departureTime = Integer.parseInt(parts[3]); 
            String destination = parts[4];           
            Location loc = finder.findLocation(destination);           
            int numCrew = Integer.parseInt(parts[5]);       
            int weightPass; 
            
            if(parts.length == 7){                
                weightPass = Integer.parseInt(parts[6]);
            }else{
                weightPass = 0;
            }
                        
            if(flightType.equals("Cargo")){
                return new CargoFlight(flightNumber, dayOfWeek, departureTime, loc, numCrew, weightPass);
            }else if(flightType.equals("Passenger")){
                return new PassengerFlight(flightNumber, dayOfWeek, departureTime, loc, numCrew, weightPass);
            }else{
                return new TrainingFlight(flightNumber, dayOfWeek, departureTime, loc, numCrew);
            }
        } catch (Exception ex) {
            System.out.println("Flight Parse error, msg=" + ex.getMessage());
            return null;
        }
    }
    
    @Override
    public boolean checkCrew(){
        return true;
    }
    @Override 
    public boolean checkPassengers(){
        return true;
    }   
    @Override
    public boolean checkTime(){
        return true;
    }
    @Override
    public boolean checkWeight(){
        return true;
    }
            
    public int calculateWeight() {
        
        int res = numCrew * Common.AVERAGE_PERSON_WEIGHT;          
        return res;
    }
    
    public String getDayOfWeek(){
        return this.dayOfWeek;
    }
    
    public int getDepartureTime(){
        return this.departureTime;
    }
    
    public Location getDestination(){
        return this.destination;
    }
    
    public String getFlightNumber(){
        return this.flightNumber;
    }
    
    abstract String getFlightType();
    
    public int getNumCrew(){
        return this.numCrew;
    }     

    public String toArchiveFormat() {
        return this.getFlightType() + "," + this.flightNumber + "," + this.dayOfWeek + "," + this.departureTime + "," + this.destination.getLocationCode() + "," + this.numCrew;
    }
    
    public String toDisplayReport() {
        return this.getFlightType() + " Flight = " + this.flightNumber + ", Day = " + this.dayOfWeek + ", Time = " + this.departureTime 
               + "\r\n\t" + "Destination: " + this.destination.getLocationCode() + " (" + this.destination.getCity() + "," + this.destination.getCountry() + "), region " + this.destination.getRegion()
               + "\r\n\t" + "Number of Crew: " + Common.format(this.numCrew)
               + "\r\n\t" + "Total Weight: " + Common.format(this.calculateWeight());
    }
} // end class Flight
