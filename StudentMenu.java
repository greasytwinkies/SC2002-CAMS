/**
    Represents menu options for student
*/

public class StudentMenu implements iMenu {

    /**
     * Method to Print the Menu for Students options.
     */
    public void printMenu() {
        System.out.println("==============================");
        System.out.println("STUDENT MENU");
        System.out.println("==============================");
        System.out.println("Enter your choice:");
        System.out.println("(1) View available camps");
        System.out.println("(2) View registered camps");
        System.out.println("(3) Register for camp");
        System.out.println("(4) Withdraw from camp");
        System.out.println("(5) Submit camp enquiry");
        System.out.println("(6) View your enquiries");
        System.out.println("(7) Edit enquiry");
        System.out.println("(8) Delete enquiry");
        System.out.println("(9) **Access Camp Committee menu**");
        System.out.println("(10) Change password");
        System.out.println("(11) Exit menu");
        System.out.println("==============================");
        System.out.println("");
    }
}