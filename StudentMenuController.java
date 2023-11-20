import java.util.Scanner;

public class StudentMenuController {
    Scanner scanner = Main.getScanner();

    public StudentMenuController() {

    }

    public int StudentMenuControl(Student student, CampList campList) {
        StudentMenu studentMenu = new StudentMenu();
        
        int choice;
        do {
            studentMenu.printMenu();
            choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1: // view camps that are open to student ie camp that is same faculty or ntu level
                    System.out.println("Viewing Available Camps"); // not working yet
                    student.viewAvailableCamps(campList);
                    break;
                case 2:
                    // if (student.getCampsRegisteredAsParticipant().list.size() == 0) {
                    //     System.out.println("You are currently not registered for any camps!");
                    //     break;
                    // }
                    System.out.println("Viewing Registered Camps");
                    // student.viewRegisteredCamps(campList);
                    campList.printUserCamp(student);
                    break;
                case 3:
                    // first, print out all available camps
                    System.out.println("Viewing Available Camps:");
                    System.out.println("Camp Name\t\tCamp Vacancies");
                    System.out.println("\t\t\t(Participant/Camp Committee)");
                    System.out.println("");
                    CampList availableCamps = student.viewAvailableCamps(campList);
                    if (availableCamps != null) {
                        System.out.println("Please indicate the camp number you would like to register for:");
                        int campChoice = Integer.valueOf(scanner.nextLine());
                        // next, get the appropriate camp from the list
                        Camp chosenCamp = (Camp) availableCamps.list.get(campChoice - 1);
                        System.out
                                .println("You have chosen to register for: " + chosenCamp.getCampInfo().getCampName());
                        int registrationChoice;
                        do {
                            System.out.println(
                                    "Select your choice:\n(1) Register as attendee\n(2) Register as Camp Committee Member");
                            registrationChoice = Integer.valueOf(scanner.nextLine());
                            switch (registrationChoice) {
                                case 1:
                                    student.registerCampAsAttendee(chosenCamp);
                                    break;
                                case 2:
                                    student.registerCampAsCampComm(chosenCamp);
                                    break;
                                default:
                                    System.out.println("Invalid choice, please try again.");
                            }
                        } while (registrationChoice < 1 || registrationChoice > 2);
                    }
                    break;
                case 4:
                    CampList registeredCamps = campList.returnUserCamps(student);
                    if (registeredCamps==null){
                        System.out.println("You are currently not registered for any camps!");
                        break;
                    }
     
                    System.out.println("Viewing registered camps:");
                    student.viewRegisteredCamps(campList);
                    System.out.println("Please indicate the camp number you would like to withdraw from:");
                    int campWithdrawChoice = Integer.valueOf(scanner.nextLine());
                    Camp withdrawCamp = (Camp) registeredCamps.getFromList(campWithdrawChoice-1);
                    student.withdrawCamp(withdrawCamp,campList);
                    System.out.println("Successfully withdrawn from " + withdrawCamp.getCampInfo().getCampName() + "!");
                    break;
                case 5:
                    System.out.println("Enquiring Camps");
                    // availableCamps = student.viewAvailableCamps(campList);
                    // if (student.getCampsRegisteredAsParticipant().list.size() == 0) {
                    //     System.out.println("Please register for a camp before enquiring. ");
                    // } else {
                    //     System.out.println("Enter the index of the camp you want to send enquiry to: ");
                    //     int enquiringCampNumber = Integer.valueOf(scanner.nextLine());
                    //     Camp enquiringCamp = (Camp) availableCamps.list.get(enquiringCampNumber - 1);
                    //     // Camp enquiringCamp = searchCamp(campList);
                    //     student.enquiryMenuController.submitEnquiry(enquiringCamp);

                    // }
                    // break;
                    System.out.println("1) Available Camps");
                    System.out.println("2) Registered Camps");
                    System.out.println("Enter the type of camps you want to submit enquiry to: ");
                    choice = Integer.valueOf(scanner.nextLine());
                    if (choice==1){
                        System.out.println("Available Camps:");
                        availableCamps = student.viewAvailableCamps(campList);
                        if (availableCamps==null){
                            System.out.println("There are no available camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to submit enquiry to: ");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) availableCamps.list.get(choice-1);
                        student.enquiryMenuController.submitEnquiry(enquiredCamp);
                    }
                    else if (choice==2){
                        campList.printUserCamp(student);
                        CampList userCamps = campList.returnUserCamps(student);
                        if (userCamps==null){
                            System.out.println("There are no registered camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to submit enquiry to:");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) userCamps.getFromList(choice-1);
                        student.enquiryMenuController.submitEnquiry(enquiredCamp);
                    }
                    break;
                case 6:
                    System.out.println("Viewing Enquiries");

                    // if (student.getCampsRegisteredAsParticipant().list.size() == 0) { // student is not in any camps
                    //                                                                   // hence no enquiries
                    //     System.out.println("No enquiries ");
                    // } else {
                    //     CampList campRegisteredAsParticipant = student.getCampsRegisteredAsParticipant();
                    //     int sizeOfRegistered = campRegisteredAsParticipant.list.size();
                    //     for (int i = 0; i < sizeOfRegistered; i++) {
                    //         Camp enquiredCamp = (Camp) campRegisteredAsParticipant.getFromList(i);
                    //         System.out.println(enquiredCamp.getCampInfo().getCampName());
                    //         student.enquiryMenuController.viewEnquiries(enquiredCamp);

                    //     }
                    // }
                    System.out.println("1) Available Camps");
                    System.out.println("2) Registered Camps");
                    System.out.println("Enter the type of camps you want to view enquiry of: ");
                    choice = Integer.valueOf(scanner.nextLine());
                    if (choice==1){
                        System.out.println("Available Camps:");
                        availableCamps = student.viewAvailableCamps(campList);
                        if (availableCamps==null){
                            System.out.println("There are no available camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to view enquiry of: ");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) availableCamps.list.get(choice-1);
                        student.enquiryMenuController.viewEnquiries(enquiredCamp);
                    }
                    else if (choice==2){
                        campList.printUserCamp(student);
                        CampList userCamps = campList.returnUserCamps(student);
                        if (userCamps==null){
                            System.out.println("There are no registered camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to view enquiry of:");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) userCamps.getFromList(choice-1);
                        student.enquiryMenuController.viewEnquiries(enquiredCamp);
                    }
                    break;
                // case 7:
                //     System.out.println("Editing Enquiry");
                //     if (student.getCampsRegisteredAsParticipant().list.size() == 0) { // student is not in any camps
                //                                                                       // hence no enquiries
                //         System.out.println("No enquiries to edit");
                //     } else {
                //         CampList campRegisteredAsParticipant = student.getCampsRegisteredAsParticipant();
                //         int sizeOfRegistered = campRegisteredAsParticipant.list.size();
                //         for (int i = 0; i < sizeOfRegistered; i++) {
                //             enquiredCamp = (Camp) campRegisteredAsParticipant.getFromList(i);
                //             student.enquiryMenuController.editEnquiry(enquiredCamp);

                //         }
                //     }
                case 7:
                    System.out.println("Editing Enquiry");
                    System.out.println("1) Available Camps");
                    System.out.println("2) Registered Camps");
                    System.out.println("Enter the type of camps you want to edit enquiry of: ");
                    choice = Integer.valueOf(scanner.nextLine());
                    if (choice==1){
                        System.out.println("Available Camps:");
                        availableCamps = student.viewAvailableCamps(campList);
                        if (availableCamps==null){
                            System.out.println("There are no available camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to edit enquiry of: ");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) availableCamps.list.get(choice-1);
                        student.enquiryMenuController.editEnquiry(enquiredCamp);
                    }
                    else if (choice==2){
                        campList.printUserCamp(student);
                        CampList userCamps = campList.returnUserCamps(student);
                        if (userCamps==null){
                            System.out.println("There are no registered camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to edit enquiry of:");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) userCamps.getFromList(choice-1);
                        student.enquiryMenuController.editEnquiry(enquiredCamp);
                    }
                    break;


                //     // if(student.viewAvailableCamps(campList)){
                //     // Camp editEnquirecamp = searchCamp(campList);
                //     // student.editEnquiry(editEnquirecamp);
                //     // }
                //     break;
                // case 8:
                //     System.out.println("Deleting Enquiry");
                //     if (!campList.list.isEmpty()) {
                //         CampList campRegisteredAsParticipant = student.getCampsRegisteredAsParticipant();
                //         int sizeOfRegistered = campRegisteredAsParticipant.list.size();
                //         for (int i = 0; i < sizeOfRegistered; i++) {
                //             Camp deleteEnquireCamp = (Camp) campRegisteredAsParticipant.getFromList(i);
                //             student.enquiryMenuController.deleteEnquiry(deleteEnquireCamp);
                //             // Camp deleteEnquireCamp = searchCamp(campList);
                //             // System.out.println("Error debugging 1");
                //             // System.out.println("Camp's Enquiry to be deleted: " + deleteEnquireCamp);
                //             // enquiryMenu.deleteEnquiry(deleteEnquireCamp);
                //         }
                //         break;

                //     }
                case 8:
                    System.out.println("Deleting Enquiry");
                    System.out.println("1) Available Camps");
                    System.out.println("2) Registered Camps");
                    System.out.println("Enter the type of camps you want to delete enquiry of: ");
                    choice = Integer.valueOf(scanner.nextLine());
                    if (choice==1){
                        System.out.println("Available Camps:");
                        availableCamps = student.viewAvailableCamps(campList);
                        if (availableCamps==null){
                            System.out.println("There are no available camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to delete enquiry of: ");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) availableCamps.list.get(choice-1);
                        student.enquiryMenuController.deleteEnquiry(enquiredCamp);
                    }
                    else if (choice==2){
                        campList.printUserCamp(student);
                        CampList userCamps = campList.returnUserCamps(student);
                        if (userCamps==null){
                            System.out.println("There are no registered camps.");
                            break;
                        }
                        System.out.println("Enter the index of the camp you want to delete enquiry of:");
                        choice = Integer.valueOf(scanner.nextLine());
                        Camp enquiredCamp = (Camp) userCamps.getFromList(choice-1);
                        student.enquiryMenuController.deleteEnquiry(enquiredCamp);
                    }
                    break;
                
                case 9:
                    CampCommMember campComm = campList.findCampCommMember(student);
                    if (campComm==null){
                        System.out.println("You are not a camp committee member.");
                        break;
                    }
                    System.out.println("Successful. Entering committee menu for " + campComm.getCamp().getCampInfo().getCampName());
                    CampCommMenuController campCommMenuController = new CampCommMenuController();
                    campCommMenuController.CampCommMenuControl(campComm);
                    break;
                
                case 10:
                    LoginPage.changeStudentPassword(student);
                    

            }
            System.out.println();
            System.out.println();
            System.out.println();
        } while (choice >= 1 && choice < 11);

        return LoginPage.Logout();
    }

    // public Camp searchCamp(CampList campList) { // not working
    // System.out.println("Enter the name of the camp: ");
    // String campName = scanner.nextLine();
    // for (int i = 0; i < campList.list.size(); i++) {
    // if (((Camp) campList.getFromList(i)).getCampInfo().getCampName() == campName)
    // {
    // return (Camp) campList.getFromList(i);
    // }
    // }
    // return null;
    // }
}