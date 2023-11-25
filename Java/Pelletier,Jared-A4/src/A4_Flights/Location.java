package A4_Flights;

//*** NOTE: this class is complete ***//
/**
 * An airport location.
 */
public class Location {

    private String locationCode;
    private String city;
    private String country;
    private int region;
    
    public interface LocationFinder{
        Location findLocation(String locationCode);
    }

    public static Location parseCSV(String line) {
        try {
            String parts[] = line.split(",");

            if (parts.length < 4)
                return null;
            String locationCode = parts[0];
            String city = parts[1];
            String country = parts[2];
            int region = Integer.parseInt(parts[3]);
            return new Location(locationCode, city, country, region);
        } catch (Exception ex) {
            System.out.println("Location Parse error, msg=" + ex.getMessage());
            return null;
        }
    }

    /**
     * Creates a location with the specified parameters.
     *
     * @param locationCode a three-letter location code
     * @param city the name of a city
     * @param country the name of a country
     * @param region an integer representing a location region
     */
    public Location(String locationCode, String city, String country, int region) {
        this.locationCode = locationCode;
        this.city = city;
        this.country = country;
        this.region = region;
    }

    /**
     * Creates a string containing this location's information, formatted for
     * displaying to a human reader.
     *
     * @return the formatted information
     */
    public String toDisplayFormat() {
        return locationCode + " (" + city + ", " + country + "), region " + region;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public int getRegion() {
        return region;
    }

} // end class Location
