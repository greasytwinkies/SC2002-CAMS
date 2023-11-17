import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StaffMenuController {
    Scanner scanner = new Scanner(System.in);

    public void StaffMenuControl(Staff staff, CampList campList) {
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
                    CampInformation campInformation = CampInformationCreater.populateCampInformation();
                    camp = new Camp(campInformation, staff);
                    campList.addToList(camp);
                    campList.printList();
                    // add to staff's list of camps
                    break;
                case 2:
                    System.out.println("Enter the Camp Name to edit it");
                    String campToEdit = scanner.nextLine();
                    camp = campList.findCamp(campList, campToEdit);
                    campInfoControl.CampInformationMenuControl(camp.getCampInfo());
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
                // case 7:
                // staff.viewEnquiries(enquiryList); // insert a enquiryList object
                // break;
                // case 8:
                // staff.replyEnquiries(enquiryList); // insert a enquiryList object
                // break;
                // case 9:
                // staff.approveCampCommitteeSuggestions(suggestionList); // insert a
                // suggestionsList object
                // break;
                // case 10:
                // System.out.println("Enter camp name to print student report");
                // String campNameForStudentReport = scanner.nextLine();
                // for (int i = 0; i < campList.list.size(); i++) {
                // if (((Camp) campList.getFromList(i)).getCampInfo().getCampName() ==
                // campNameForStudentReport) {
                // staff.generateStudentList((Camp) campList.getFromList(i));
                // }
                // }
                // break;
                // case 11:
                // System.out.println("Enter camp name to generate camp committee member
                // report");
                // String campNameForCampCommitteeMemberReport = scanner.nextLine();

                // for (int i = 0; i < campList.list.size(); i++) {
                // if (((Camp) campList.getFromList(i)).getCampInfo()
                // .getCampName() == campNameForCampCommitteeMemberReport) {
                // staff.generateCampCommitteeMemberList((Camp) campList.getFromList(i));
                // }
                // }
                // break;
                // case 12:
                // break;
                case 13:
                    String[] mainArgs = new String[] {}; // Or any arguments you want to pass
                    Main.main(mainArgs);
                    break;
            }
        } while (choice > 0 && choice <= 13);

    }
}