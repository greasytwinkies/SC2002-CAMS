import java.util.Scanner;

public class CampCommMenuController {
    Scanner scanner = Main.getScanner();

    public void CampCommMenuControl(CampCommMember campComm) {
        CampCommMenu campCommMenu = new CampCommMenu();
        Camp camp = campComm.getCamp();
        campCommMenu.printMenu();
        int choice;
        do {
            choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println(camp.getCampInfo().getCampName());
                    //TODO print out all the camp info...
                    break;
            //     case 2:
            //         campComm.submitSuggestions();
            //         break;
            //     case 3:
            //         campComm.editMySuggestions();
            //         break;
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