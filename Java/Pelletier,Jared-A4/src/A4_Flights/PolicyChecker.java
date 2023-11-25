package A4_Flights;

//*** NOTE: this class is not needed until Part D ***//

/**
 * Methods to run data checks and create error reports.
 */
public class PolicyChecker {

    private static final String PREFIX = "    * ";

    // Mark the default constructor private to prevent instantiation
    // (this class contains only static methods)
    //
    private PolicyChecker() {
    }

    /**
     * Checks each of the items to see if it satisfies the rules in the
     * PolicyRules interface.
     *
     * @param items the items to check
     * @return an array of messages - "OK" if all rules are met, or an error
     * message.
     */
    public static String[] runPolicyChecks(PolicyRules[] items) {
        String[] res = new String[items.length];
        for (int i = 0; i <= items.length - 1; i++) {
            String sErrorMessage = "";
            PolicyRules tempItem = items[i];

            if (!tempItem.checkTime())
                sErrorMessage = PREFIX + "Time Violation";

            if (!tempItem.checkWeight()) {
                if (sErrorMessage.length() > 0)
                    sErrorMessage += "\n";
                sErrorMessage += PREFIX + "Weight Violation";
            }

            if (!tempItem.checkCrew()) {
                if (sErrorMessage.length() > 0)
                    sErrorMessage += "\n";
                sErrorMessage += PREFIX + "Minimum Crew Violation";
            }

            if (!tempItem.checkPassengers()) {
                if (sErrorMessage.length() > 0)
                    sErrorMessage += "\n";
                sErrorMessage += PREFIX + "Minimum Passenger Violation";
            }

            if (sErrorMessage.length() == 0)
                sErrorMessage = Common.NO_ERROR;
            res[i] = sErrorMessage;
        }

        return res;
    }
} // end class ErrorReporter

