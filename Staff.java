
import java.time.LocalDate;
import java.util.Scanner;

public class Staff extends User {

    // ArrayList<String> participantMembersList = new ArrayList<String>();
    // ArrayList<String> campCommitteeMembersList = new ArrayList<String>();
    // CampList participantMembersList = new CampList();
    // CampList campCommitteeMembersList = new CampList();
    // CampList campMembersList = new CampList();
    // CampList campList = new CampList(); // not confirmed yet ... suggestion: implement a global list

    Scanner scanner = new Scanner(System.in);

    public Staff(String name, String userID, String password, Faculty facultyInformation) {
        super(name, userID, password, facultyInformation);
    }

    // public void createCamp(String campName, int campComitteeSlots, int campParticipantSlots, String description,
    //         LocalDate startingDate, LocalDate endingDate, LocalDate registrationClosingDate, String location,
    //         int totalCampCommitteeSlots, int totalParticipantSlots, String staffName) {
        
    //     Camp camp = new Camp(campName, startingDate, endingDate, registrationClosingDate, true, location, totalParticipantSlots, totalCampCommitteeSlots, description, staffName); // this also creates an empty campInfo object
    //     while (true) {
    //         System.out.println("Enter a name to add (or type 'exit' to quit):");
    //         String name = scanner.nextLine();

    //         if (name.equalsIgnoreCase("exit")) {
    //             break;
    //         }

    //         System.out.println("Choose a list to add the name to:");
    //         System.out.println("1: Participant Members List");
    //         System.out.println("2: Camp Committee Members List");
    //         System.out.println("3: Camp Members List");

    //         int choice = scanner.nextInt();
    //         scanner.nextLine(); // Consume the leftover newline

    //         switch (choice) {
    //             case 1:
    //                 participantMembersList.addToList(name);
    //                 System.out.println("Added to Participant Members List.");
    //                 break;
    //             case 2:
    //                 campCommitteeMembersList.addToList(name);
    //                 System.out.println("Added to Camp Committee Members List.");
    //                 break;
    //             case 3:
    //                 campMembersList.addToList(name);
    //                 System.out.println("Added to Camp Members List.");
    //                 break;
    //             default:
    //                 System.out.println("Invalid choice. Please try again.");
    //         }
    //     }

    //     scanner.close();
    //     // camp.getCampInfo().setCampCommitteeMembers(); // empty CampCommitteeMemberList is created
    //     // camp.getCampInfo().setCampMembers(); // empty CampMembersList is created
    //     camp.getCampInfo().setCampName(campName);
    //     camp.getCampInfo().setCurrentCampCommitteeSlots(0); // initially 0
    //     camp.getCampInfo().setCurrentParticipantSlots(0); // initially 0
    //     camp.getCampInfo().setDescription("Unknown Description");
    //     camp.getCampInfo().setEndingDate(endingDate);
    //     camp.getCampInfo().setLocation(location);
    //     // camp.setParticipantMembers(); // not found in campInformation
    //     camp.getCampInfo().setRegistrationClosingDate(registrationClosingDate);
    //     camp.getCampInfo().setStaffInCharge(null); // StaffInCharge needs to be an object
    //     camp.getCampInfo().setStartingDate(startingDate);
    //     camp.getCampInfo().setTotalCampCommitteeSlots(totalCampCommitteeSlots);
    //     camp.getCampInfo().setTotalParticipantSlots(totalParticipantSlots);
    //     // camp.getCampInfo().setUserGroup(); // unsure where this userGroup is
    //     campList.addToList(camp);
    // }

    // public Camp findAndEditCamp(String campName) {
    //     for (int i = 0; i < campList.list.size(); i++) {
    //         if (((Camp) campList.getFromList(i)).getCampInfo().getCampName() == campName) {
    //             return (Camp) campList.getFromList(i);
    //         }
    //     }
    //     return null;
    // }

    // public boolean deleteCamp(String campName) {
    //     for (int i = 0; i < campList.list.size(); i++) {
    //         if (((Camp) campList.getFromList(i)).getCampInfo().getCampName() == campName) {
    //             campList.deleteFromList(((Camp) campList.getFromList(i)));
    //             System.out.println("Successfully removed " + campName + "from campList!");
    //             return true;
    //         }
    //     }
    //     System.out.println("Could not find " + campName + " in camp list");
    //     return false;
    // }

    // public boolean toggleCampVisibility(String campName, boolean isVisible) {
    //     for (int i = 0; i < campList.list.size(); i++) {
    //         if (((Camp) campList.getFromList(i)).getCampInfo().getCampName() == campName) {
    //             ((Camp) campList.getFromList(i)).getCampInfo().setCampVisibility(isVisible);
    //             System.out.println("Camp Visibility Set!");
    //             return true;
    //         }
    //     }
    //     System.out.println("Could not find " + campName + " in camp list");
    //     return false;
    // }

    // public void viewAllCamps(CampList campList){ // prints out every campInformation
    //     for(int i = 0; i < campList.list.size(); i++){
    //         ((Camp) campList.getFromList(i)).printCampInfo();
    //     }
    //     // campList.printList(); // alternative method that prints campNames
    // }

    // public void viewCreatedCamps(){ // prints out every campInformation for that Staff
    //     for(int i = 0; i < campList.list.size(); i++){
    //         if(((Camp) campList.getFromList(i)).getCampInfo().getStaffInCharge() == super.getName()){
    //             ((Camp) campList.getFromList(i)).printCampInfo();
    //         }
    //     }
    // }

    // public void viewEnquiries(EnquiryList enquiryList){
    //     enquiryList.printList();
    // }

    // public void replyEnquiries(EnquiryList enquiryList){
    //     enquiryList.replyEnquiries();
    // }

    // public void approveCampCommitteeSuggestions(SuggestionList suggestionList){
    //     suggestionList.approveSuggestions();
    // }

    // public void generateStudentList(Camp camp){
    //     camp.getCampAttendeesList();
    // }

    // public void generateCampCommitteeMemberList(Camp camp){
    //     camp.getCampCommitteeMembersList();
    //}


}