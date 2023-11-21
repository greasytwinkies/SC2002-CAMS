import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);

    public static Scanner getScanner(){
        return scan;
    }

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
    


