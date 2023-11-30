
import java.util.Scanner;
/**
    Represents a Staff object, which is a subclass of the User superclass.
*/
public class Staff extends User {
    /**
    * The list of camps created by the staff.
    */
    CampList campList = new CampList(getName());

    /**
    * Scanner
    */
    Scanner scanner = Main.getScanner();

    /**
    * Creates a Staff object with the given staff Name, userID, password and faculty information.
    * @param name The name of the given staff.
    * @param userID The username of the given staff's account.
    * @param password The password of the given staff's account.
    * @param facultyInformation The faculty which the staff belongs to.
    */
    public Staff(String name, String userID, String password, Faculty facultyInformation) {
        super(name, userID, password, facultyInformation);
    }

    /**
    * Allows for the given camp to be added to the Staff's list of created camps.
    * @param camp The camp to be added to the Staff's list of created camps.
    */
    public void addCampToList(Camp camp) {
        campList.addToList(camp);
    }

    /**
    * Allows Staff to view all camps (even those not created by themselves).
    */
    public void viewAllCamps() {
        campList.printList();
    }

}