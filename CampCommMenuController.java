import java.util.Scanner;

public class CampCommMenuController {
    Scanner scanner = Main.getScanner();

    public void CampCommMenuControl(CampCommMember campComm) {
        CampCommMenu campCommMenu = new CampCommMenu();
        Camp camp = campComm.getCamp();
        
        int choice;
        do {
            campCommMenu.printMenu();
            choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println(camp.getCampInfo().getCampName());
                    //TODO print out all the camp info... maybe do in camp info class
                    break;
                case 2:
                    campComm.viewMySuggestions();
                case 3:
                    Suggestion suggestion = new Suggestion(campComm);
                    campComm.submitSuggestions(suggestion);
                    break;
                case 4:
                    campComm.viewMySuggestions();
                    System.out.println("Enter number of suggestion to be edited: ");
                    int idx = Integer.valueOf(scanner.nextLine());
                    campComm.getCamp().getSuggestions().editSuggestions(idx);
                    break;
                case 5:
                    campComm.viewMySuggestions();
                    System.out.println("Enter number of suggestion to be deleted: ");
                    int del = Integer.valueOf(scanner.nextLine());
                    campComm.getCamp().getSuggestions().deleteFromList(del);
                    System.out.println("Suggestion successfully deleted.");
                    break;
                case 9:
                    System.out.println("EXITING CAMP COMMITTEE MEMBER MENU");
                    System.out.println("");
                    System.out.println("RETURNING TO STUDENT MENU");
                    break;
            //     case 4:
            //         campComm.deleteMySuggestions();
            //         break;
            //     case 5:
            //         campComm.viewEnquiries();
            //         break;
            //     case 6:
            //         campComm.replyEnquiries();
            //         break;
            //     case 7:
            //         campComm.generateReport();
            //         break;
            //     case 8:
            //         break;
            }
        } while (choice > 0 && choice < 9);
    }
    
}