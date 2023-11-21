import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StaffMenuController {
    Scanner scanner = Main.getScanner();

    public int StaffMenuControl(Staff staff, CampList campList) throws FileNotFoundException {
        StaffMenu staffMenu = new StaffMenu();
        CampInformationMenuController campInfoControl = new CampInformationMenuController();

        int choice;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Camp camp;
        do {
            staffMenu.printMenu();
            choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    CampInformation campInformation = CampInformationCreater.populateCampInformation(staff, campList);
                    camp = new Camp(campInformation, staff);
                    campList.addToList(camp);
                    campList.printList();
                    // add to staff's list of camps
                    break;
                case 2:
                    System.out.println("Enter the Camp Name to edit it");
                    String campToEdit = scanner.nextLine();
                    camp = campList.findCamp(campList, campToEdit);
                    campInfoControl.CampInformationMenuControl(camp.getCampInfo(), campList);
                    break;
                case 3:
                    System.out.println("Enter the Camp Name to delete it: ");
                    String campToDelete = scanner.nextLine();
                    camp = campList.findCamp(campList, campToDelete);
                    campList.deleteFromList(camp);
                    // delete from staff's list of camps too
                    break;
                case 4:
                    System.out.println("Enter the Camp Name to toggle visibility: ");
                    String campToggleVisbility = scanner.nextLine();
                    camp = campList.findCamp(campList, campToggleVisbility);
                    campInfoControl.toggleCampVisibility(camp.getCampInfo());
                    break;
                case 5:
                    campList.printList();
                    break;
                case 6:
                    campList.printUserCamp(staff);
                    break;
                case 7:
                    CampList userCamps = campList.returnUserCamps(staff);
                    System.out.println("Enter the index of the camp you want to view enquiry of:");
                    choice = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(choice-1);
                    camp.getEnquiries().printList();
                    break;
                case 8:
                    userCamps = campList.returnUserCamps(staff);
                    System.out.println("Enter the index of the camp you want to reply enquiry to:");
                    choice = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(choice-1);
                    camp.getEnquiries().replyEnquiries();
                    break;
                /* where code modification starts */
                case 9: // view camp suggestions
                    userCamps = campList.returnUserCamps(staff);
                    System.out.println("Enter the index of the camp you want to view suggestions of:");
                    choice = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(choice-1);
                    camp.getSuggestions().printList();
                    break;
                case 10: // approve camp suggestions
                    userCamps = campList.returnUserCamps(staff);
                    System.out.println("Enter the index of the camp you want to view suggestions of:");
                    choice = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(choice-1);
                    camp.getSuggestions().approveSuggestions();
                    break;
                case 11: // student report
                    // first, prompt user if they want to print student report for 1) all your created camps 2) a specific camp
                    // next, prompt user for micro-level filtering: 1) all attendees 2) participants only 3) camp comm only 4) specific individual.
                    StudentReport studentReport = new StudentReport();
                    userCamps = campList.returnUserCamps(staff);
                    System.out.println("Print student report for 1) All your created camps or 2) A specific camp\n");
                    int campFilter = Integer.valueOf(scanner.nextLine());
                    if (campFilter == 1) { // print all camps
                        studentReport.printReportsForAllCamps(userCamps);
                    }
                    else if (campFilter == 2) { // print one camp only
                        campList.printUserCamp(staff);
                        userCamps = campList.returnUserCamps(staff);
                        System.out.println("Enter index of camp you want to generate a student report for:\n");
                        choice = Integer.valueOf(scanner.nextLine());
                        camp = (Camp) userCamps.getFromList(choice-1);
                        studentReport.printReport(camp);
                    }
                    System.out.println("Student reports printed to studentReport.txt");
                    break;
                case 12:
                    System.out.println("Print camp committee performance report for 1) All your camps or 2) A specific camp\n");
                    choice = Integer.valueOf(scanner.nextLine());
                    userCamps = campList.returnUserCamps(staff);
                    if (choice == 1) {
                        System.out.println("Printing Camp Committee Member performance report for ALL your camps:\n");
                        for (int i=0; i<=userCamps.list.size(); i++) {
                            camp = (Camp) userCamps.getFromList(i);
                            System.out.println(camp.getCampInfo().getCampName() + ":");
                            CampCommitteeReport campCommitteeReport = new CampCommitteeReport();
                            campCommitteeReport.printReport(camp);
                        }
                    }
                    if (choice == 2) {
                        campList.printUserCamp(staff);
                        System.out.println("Enter the index of the camp you want to generate a committee member report for:\n");
                        choice = Integer.valueOf(scanner.nextLine());
                        camp = (Camp) userCamps.getFromList(choice-1);
                        System.out.println("Printing Camp Committee Member Report for " + camp.getCampInfo().getCampName() + ":");
                        CampCommitteeReport campCommitteeReport = new CampCommitteeReport();
                        campCommitteeReport.printReport(camp);
                    }
                    break;
                case 13:
                    LoginPage.changeStaffPassword(staff);
                    System.out.println("Please log in again.");
                    LoginPage.Logout();
                    break;
                    
            }
            System.out.println();
            System.out.println();
            System.out.println();
        } while ((choice > 0 && choice < 14) && LoginPage.getLogout()==0);

        return LoginPage.Logout();

    }
}