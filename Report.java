import java.io.FileNotFoundException;

/**
 * An interface that implements a Report 
 */

public interface Report {

    /**
     * Method for other classes to implement reports
     * @param camp takes in a camp object
     * @throws FileNotFoundException When this file cannot be found
     */
    public void printReport(Camp camp) throws FileNotFoundException;
}