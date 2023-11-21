import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StaffMenuController {
    Scanner scanner = Main.getScanner();

    public int StaffMenuControl(Staff staff, CampList campList) {
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
                    System.out.println("Print student report for 1) All your camps or 2) A specific camp\n");
                    choice = Integer.valueOf(scanner.nextLine());
                    userCamps = campList.returnUserCamps(staff);
                    if (choice == 1) {
                        // TODO: implement this function
/*                         for (int i=0; i<=userCamps.list.size(); i++) {
                            camp = (Camp) userCamps.getFromList(i);
                            System.out.println("Printing student report for " + camp.getCampInfo().getCampName() + ":\n");
                            StudentReport studentReport = new StudentReport();
                            studentReport.printReport(camp);
                        } */
                    }
                    if (choice == 2) {
                        campList.printUserCamp(staff);
                        userCamps = campList.returnUserCamps(staff);
                        System.out.println("Enter index of camp you want to generate a student report for:\n");
                        choice = Integer.valueOf(scanner.nextLine());
                        camp = (Camp) userCamps.getFromList(choice-1);
                        System.out.println("Printing student report for " + camp.getCampInfo().getCampName() + ":\n");
                        StudentReport studentReport = new StudentReport();
                        studentReport.printReport(camp);
                    }
                    break;
                case 12:
                    System.out.println("Print camp committee performance report for 1) All your camps or 2) A specific camp\n");
                    choice = Integer.valueOf(scanner.nextLine());
                    userCamps = campList.returnUserCamps(staff);
                    if (choice == 1) {
                        for (int i=0; i<=userCamps.list.size(); i++) {
                            camp = (Camp) userCamps.getFromList(i);
                            System.out.println("Printing camp committee member performance report for " + camp.getCampInfo().getCampName() + ":\n");
                            CampCommitteeReport campCommitteeReport = new CampCommitteeReport();
                            campCommitteeReport.printReport(camp);
                        }
                    }
                    if (choice == 2) {
                        campList.printUserCamp(staff);
                        System.out.println("Enter the index of the camp you want to generate a committee member report for:\n");
                        choice = Integer.valueOf(scanner.nextLine());
                        camp = (Camp) userCamps.getFromList(choice-1);
                        System.out.println("Printing camp committee member performance report for " + camp.getCampInfo().getCampName() + ":\n");
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