import java.util.Scanner;

public class StudentMenuController {
    Scanner scanner = new Scanner(System.in);

    public StudentMenuController() {

    }

    public int StudentMenuControl(Student student, CampList campList) {
        StudentMenu studentMenu = new StudentMenu();
        studentMenu.printMenu();
        int choice = Integer.valueOf(scanner.nextLine());
        do {
            switch (choice) {
                case 1: // view camps that are open to student ie camp that is same faculty or ntu level
                    System.out.println("Viewing Available Camps"); // not working yet
                    student.viewAvailableCamps(campList);
                    break;
                case 2:
                    if (student.getCampsRegisteredAsParticipant().list.size() == 0) {
                        System.out.println("You are currently not registered for any camps!");
                        break;
                    }
                    System.out.println("Viewing Registered Camps");
                    student.viewRegisteredCamps();
                    break;
                case 3:
                    // first, print out all available camps
                    System.out.println("Viewing Available Camps:");
                    CampList availableCamps = student.viewAvailableCamps(campList);
                    if (availableCamps != null) {
                        System.out.println("Please indicate the camp number you would like to register for:");
                        int campChoice = Integer.valueOf(scanner.nextLine());
                        // next, get the appropriate camp from the list
                        Camp chosenCamp = (Camp)availableCamps.list.get(campChoice-1);
                        System.out.println("You have chosen to register for: " + chosenCamp.getCampInfo().getCampName());
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
                    if (student.getCampsRegisteredAsParticipant().list.size() == 0) {
                        System.out.println("You are currently not registered for any camps!");
                        break;
                    }
                    System.out.println("Viewing registered camps:");
                    student.viewRegisteredCamps();
                    System.out.println("Please indicate the camp number you would like to withdraw from:");
                    int campWithdrawChoice = Integer.valueOf(scanner.nextLine());
                    Camp withdrawCamp = (Camp)student.getCampsRegisteredAsParticipant().list.get(campWithdrawChoice-1);
                    student.withdrawCamp(withdrawCamp);
                    System.out.println("Successfully withdrawn from " + withdrawCamp.getCampInfo().getCampName() + "!");
                    break;
/*                 case 5:
                    System.out.println("Enquiring Camps");
                    if(student.viewAvailableCamps(campList)){
                        System.out.println("Enter the index of the camp you want to send enquiry to: ");
                        Camp enquiringCamp = searchCamp(campList);
                        student.submitEnquiry(enquiringCamp);
                    }
                    break;
                case 6:
                    System.out.println("Viewing Enquiries");
                    if(student.viewAvailableCamps(campList)){
                        Camp viewingEnquireCamp = searchCamp(campList);
                        student.viewEnquiries(viewingEnquireCamp);
                    }
                    break;
                case 7:
                    System.out.println("Editing Enquiry");
                    if(student.viewAvailableCamps(campList)){
                        Camp editEnquirecamp = searchCamp(campList);
                        student.editEnquiry(editEnquirecamp);
                    }
                    break;
                case 8:
                    System.out.println("Deleting Enquiry");
                    if(student.viewAvailableCamps(campList)){
                        Camp deleteEnquireCamp = searchCamp(campList);
                        student.deleteEnquiry(deleteEnquireCamp);
                    }
                    break; */
                
            }
            System.out.println();
            System.out.println();
            System.out.println();
            studentMenu.printMenu();
            choice = Integer.valueOf(scanner.nextLine());
        } while (choice >= 1 && choice < 9);

        return LoginPage.Logout();
    }

    public Camp searchCamp(CampList campList) { // returns the searched campObject
        System.out.println("Enter the name of the camp: ");
        String campName = scanner.nextLine();
        for (int i = 0; i < campList.list.size(); i++) {
            if (((Camp) campList.getFromList(i)).getCampInfo().getCampName() == campName) {
                return (Camp) campList.getFromList(i);
            }
        }
        return null;
    }
}