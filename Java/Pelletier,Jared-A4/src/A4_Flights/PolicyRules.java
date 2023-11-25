package A4_Flights;

//*** NOTE: this interface is not needed until Part D ***//

/**
 * Rules a flight must obey to conform to airline policy.
 */
public interface PolicyRules {
    
    // TODO-D1 - make Flight class implement the PolicyRules interface
    // Then, implement the methods in each of the 3 sub-classes

    /**
     * Does the flight have the required minimum number of crew members?
     *
     * @return <code>true</code> if the number of crew is acceptable
     */
    public boolean checkCrew();

    /**
     * Does the flight have the required minimum number of passengers?
     *
     * @return <code>true</code> if the number of passengers is acceptable
     */
    public boolean checkPassengers();

    /**
     * Does the flight have an acceptable departure time?
     * 
     * @return <code>true</code> if the departure time is acceptable
     */
    public boolean checkTime();
    
    /**
     * Does the flight have an acceptable total weight?
     * 
     * @return <code>true</code> if the total weight
     */
    public boolean checkWeight();
    
} // end interface PolicyRules
