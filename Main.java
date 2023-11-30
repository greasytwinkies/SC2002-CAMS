import java.io.FileNotFoundException;
import java.util.Scanner;

/**
    Represents the main class of the directory.
*/

public class Main {

    /**
    * Scanner to read inputs; This scanner is shared by all other classes in the directory.
    */
    public static Scanner scan = new Scanner(System.in);

    /**
    * Returns the scanner object initialized in the main class, such that it can be used in other classes.
    * @return The initialized scanner object.
    */
    public static Scanner getScanner(){
        return scan;
    }

    /**
     * Main method for CAMS.
     * @param args The command line arguments.
     * @throws FileNotFoundException When a specified file cannot be found.
     **/
    public static void main(String[] args) throws FileNotFoundException {


        CampList campList = new CampList("campList");

        int logout;

        do{
        logout = 0;
        Object user = LoginPage.login(); //returns either a staff student or null

        if(user == null){
            System.out.println("Would you like to log in again? 1)yes 2)no");
            int choice = scan.nextInt();
            if(choice == 1){
                logout = 1;
            }
            else if (choice == 2){
                logout = 0;
            }

        }

        if( user instanceof Student){
            Student student = (Student) user;
            StudentMenuController studentMenuController = new StudentMenuController();
            logout = studentMenuController.StudentMenuControl(student, campList);

        }
        else if (user instanceof Staff) {
            Staff staff = (Staff) user;
            StaffMenuController staffMenuController = new StaffMenuController();
            logout =  staffMenuController.StaffMenuControl(staff, campList);
        }
        }while(logout == 1);
}
}
    


