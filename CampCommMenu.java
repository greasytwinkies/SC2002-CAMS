/**
    Represents menu options for camp committee member
*/

public class CampCommMenu implements iMenu {
    /**
     * prints menu options for what a camp committee can do
     */
    public void printMenu() {
        System.out.println("==============================");
        System.out.println("CAMP COMMITTEE MENU");
        System.out.println("==============================");
        System.out.println("Enter your choice:");
        System.out.println("(1) View Camp Details");
        System.out.println("(2) View your suggestions");
        System.out.println("(3) Submit suggestion");
        System.out.println("(4) Edit suggestion");
        System.out.println("(5) Delete suggestion");
        System.out.println("(6) View your camp's enquiries");
        System.out.println("(7) Reply to your camp's enquiries");
        System.out.println("(8) Generate student report");
        System.out.println("(9) Exit Menu");
        System.out.println("==============================");
        System.out.println("");
    }
    
}