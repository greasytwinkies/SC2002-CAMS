
import java.util.Scanner;

public class Staff extends User {

    CampList campList = new CampList(getName()); // name of list will be staff's name for now

    Scanner scanner = Main.getScanner();

    public Staff(String name, String userID, String password, Faculty facultyInformation) {
        super(name, userID, password, facultyInformation);
    }

    public void addCampToList(Camp camp) {
        campList.addToList(camp);
    }

    public void viewAllCamps() {
        campList.printList();
    }

}