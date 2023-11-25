import java.io.FileNotFoundException;

/**
 * An interface that implements a Report 
 */

public interface Report {

    /**
     * Method for other classes to implement reports
     * @param camp
     * @throws FileNotFoundException
     */
    public void printReport(Camp camp) throws FileNotFoundException;
}