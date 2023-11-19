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
                    campComm.viewMySuggestions();
                    break;
                case 2:
                    Suggestion suggestion = new Suggestion(campComm);
                    campComm.submitSuggestions(suggestion);
                    break;
                case 3:
                    System.out.println("Editing suggestions: ");
                    campComm.editMySuggestions();
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
        } while (choice < 9 || choice > 1);
    }
    
}