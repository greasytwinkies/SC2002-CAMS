import java.io.FileNotFoundException;
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
                    System.out.print(camp.getCampInfo().toString());
                    break;
                case 2:
                    campComm.viewMySuggestions();
                    break;
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
                case 6:
                    campComm.getCamp().getEnquiries().printList();
                    break;
                case 7:
                    campComm.getCamp().getEnquiries().replyEnquiries();
                    break;
                case 8:
                    StudentReport studentReport = new StudentReport();
                    try {
                        studentReport.printReport(camp);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Student report printed to studentReport.txt");
                    break;
                case 9:
                    System.out.println("EXITING CAMP COMMITTEE MEMBER MENU");
                    System.out.println("");
                    System.out.println("RETURNING TO STUDENT MENU");
                    break;
            }
        } while (choice > 0 && choice < 9);
    }
    
}