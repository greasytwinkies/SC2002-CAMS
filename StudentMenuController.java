import java.util.Scanner;

public class StudentMenuController {
    Scanner scanner = new Scanner(System.in);

    public StudentMenuController() {

    }

    public void StudentMenuControl(Student student, CampList campList) {
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
                    System.out.println("Viewing Registered Camps");
                    student.viewRegisteredCamps();
                    break;
                case 3: // register as attendeee
                    System.out.println("Registering Camp as an Attendee");
                    if (student.viewAvailableCamps(campList)) { // can find camps
                        Camp registerCamp = searchCamp(campList);
                        System.out.println(
                                "Select your choice:\n(1) Register as attendee\n(2)Register as Camp Committee Member");
                        int no = Integer.valueOf(scanner.nextLine());
                        switch (no) {
                            case 1:
                                student.registerCampAsAttendee(registerCamp);
                                break;
                            case 2:
                                student.registerCampAsCampComm(registerCamp);
                            default:
                                System.out.println("Please try again.");
                                no = Integer.valueOf(scanner.nextLine());
                                break;
                        }
                    }
                    break;
                case 4: // withdraw
                    System.out.println("Withdrawing from camp");
                    if(student.viewAvailableCamps(campList)){
                        Camp withdrawCamp = searchCamp(campList);
                        student.withdrawCamp(withdrawCamp);
                    }
                    break;
                case 5:
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
                    break;
                case 9:
                    break;
            }
            System.out.println();
            System.out.println();
            System.out.println();
            studentMenu.printMenu();
            choice = Integer.valueOf(scanner.nextLine());
        } while (choice >= 1 && choice < 9);
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