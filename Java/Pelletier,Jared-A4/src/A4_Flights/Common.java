package A4_Flights;

//*** NOTE: this class is complete ***//

import java.text.NumberFormat;

/**
 * Some useful constants and methods
 */
public class Common {

    // Mark the default constructor private to prevent instantiation
    // (this class contains only static methods)
    //
    private Common() {} 

    private static NumberFormat formatter = NumberFormat.getNumberInstance();
    
    // Use these constants to avoid typing errors.
    public static final String CARGO = "Cargo";
    public static final String PASSENGER = "Passenger";
    public static final String TRAINING = "Training";
    
    public static final String FLIGHT_TYPE = "Flight Type";
    public static final String DESTINATION = "Destination";
    public static final String POLICY_ERRORS = "Policy Errors";
    
    public static final String ARCHIVE_FORMAT = "Archive Format";
    public static final String DISPLAY_FORMAT = "Display Format";
    
    public static final String SUMMARY_FLIGHT_TYPE = "Summary By Flight Type";
    public static final String SUMMARY_DESTINATION = "Summary By Destination";
    
    // Flight related constants
    public static final int AVERAGE_PERSON_WEIGHT = 75;
    public static final int MINIMUM_CREW = 3;
    public static final int MAXIMUM_WEIGHT = 50000;
    public static final int MINIMUM_PASSENGERS = 10;
    public static final int EARLIEST_DEPARTURE = 500;
    public static final int LATEST_DEPARTURE = 2300;

    public static final String NO_ERROR = "OK";

    /**
     * Formats the specified <code>double</code> value
     *
     * @param val the number to format
     * @return a formatted String representation
     */
    public static String format(double val) {
        return formatter.format(val);
    }

    /**
     * Formats the specified <code>float</code> value
     *
     * @param val the number to format
     * @return a formatted String representation
     */
    public static String format(float val) {
        return formatter.format(val);
    }

    /**
     * Formats the specified <code>int</code> value
     *
     * @param val the number to format
     * @return a formatted String representation
     */
    public static String format(int val) {
        return formatter.format(val);
    }
    
} // end class Common

