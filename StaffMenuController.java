import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StaffMenuController {
    Scanner scanner = new Scanner(System.in);

    public void StaffMenuControl(Staff staff, CampList campList) {
        StaffMenu staffMenu = new StaffMenu();
        staffMenu.printMenu();
        int choice = Integer.valueOf(scanner.nextLine());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        do {
            switch (choice) {
                case 1:
                    CampInformation campInformation = CampInformationCreater.populateCampInformation();
                    Camp camp = new Camp(campInformation);
                    campList.addToList(camp);
                    campList.printList();
                    //add camp to staff
                    break;
            //     case 2:
            //         System.out.println("Enter the Camp Name to edit it");
            //         String campToEdit = scanner.nextLine();
            //         staff.findAndEditCamp(campToEdit);
            //         break;
            //     case 3:
            //         System.out.println("Enter the Camp Name to delete it: ");
            //         String campToDelete = scanner.nextLine();
            //         staff.deleteCamp(campToDelete);
            //         break;
            //     case 4:
            //         System.out.println("Enter the Camp Name to toggle visibility: ");
            //         String campToggleVisbility = scanner.nextLine();
            //         System.out.println("Enter Y/N for True/False: ");
            //         if (scanner.nextLine().toUpperCase().equals("Y")) {
            //             staff.toggleCampVisibility(campToggleVisbility, true);
            //         } else if (scanner.nextLine().toUpperCase().equals("N")) {
            //             staff.toggleCampVisibility(campToggleVisbility, false);
            //         }
            //         break;
            //     case 5:
            //         staff.viewAllCamps(campList); // insert a campList object
            //         break;
            //     case 6:
            //         staff.viewCreatedCamps();
            //         break;
            //     case 7:
            //         staff.viewEnquiries(enquiryList); // insert a enquiryList object
            //         break;
            //     case 8:
            //         staff.replyEnquiries(enquiryList); // insert a enquiryList object
            //         break;
            //     case 9:
            //         staff.approveCampCommitteeSuggestions(suggestionList); // insert a suggestionsList object
            //         break;
            //     case 10:
            //         System.out.println("Enter camp name to print student report");
            //         String campNameForStudentReport = scanner.nextLine();
            //         for (int i = 0; i < campList.list.size(); i++) {
            //             if (((Camp) campList.getFromList(i)).getCampInfo().getCampName() == campNameForStudentReport) {
            //                 staff.generateStudentList((Camp) campList.getFromList(i));
            //             }
            //         }
            //         break;
            //     case 11:
            //         System.out.println("Enter camp name to generate camp committee member report");
            //         String campNameForCampCommitteeMemberReport = scanner.nextLine();

            //         for (int i = 0; i < campList.list.size(); i++) {
            //             if (((Camp) campList.getFromList(i)).getCampInfo()
            //                     .getCampName() == campNameForCampCommitteeMemberReport) {
            //                 staff.generateCampCommitteeMemberList((Camp) campList.getFromList(i));
            //             }
            //         }
            //         break;
            //     case 12:
            //         break;
            }
        } while (choice > 12 || choice < 1);
    }
}