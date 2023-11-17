import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CampInformationCreater {
    public static CampInformation populateCampInformation(Staff staff){
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        CampInformation campInformation = new CampInformation();


        System.out.println("Do you want your camp to be visible?: (true/false)");
        campInformation.setCampVisibility(scanner.nextBoolean());
        scanner.nextLine();

        System.out.println("Enter the Camp Name: ");
        String campName = scanner.nextLine();
        campInformation.setCampName(campName);

        System.out.println("Enter the Camp Starting Date: ");
        String startingDateUnformatted = scanner.nextLine();
        LocalDate startingDate = LocalDate.parse(startingDateUnformatted, formatter);
        campInformation.setStartingDate(startingDate);

        System.out.println("Enter the Camp Ending Date: ");
        String endingDateUnformatted = scanner.nextLine();
        LocalDate endingDate = LocalDate.parse(endingDateUnformatted, formatter);
        campInformation.setEndingDate(endingDate);

        System.out.println("Enter the Camp Registration Closing Date: ");
        String registrationClosingDateUnformatted = scanner.nextLine();
        LocalDate registrationClosingDate = LocalDate.parse(registrationClosingDateUnformatted, formatter);
        campInformation.setRegistrationClosingDate(registrationClosingDate);


        System.out.println("Enter the faculty: 0 for NTU-wide and 1 for faculty-specific");
        int ans = scanner.nextInt();
        if(ans == 0){
            campInformation.setFaculty(Faculty.NTU);
        }else if(ans == 1){
            campInformation.setFaculty(staff.getFacultyInformation());
        }
        scanner.nextLine();
        
        System.out.println("Enter the Camp Location ");
        campInformation.setLocation(scanner.nextLine());

        System.out.println("Enter the total number of Camp Participant Slots: ");
        int campParticipantSlots = scanner.nextInt();
        campInformation.setTotalParticipantSlots(campParticipantSlots);
        campInformation.setCurrentParticipantSlots(campParticipantSlots);
    
        System.out.println("Enter the total number of camp Comittee Slots: ");
        int campCommSlots = scanner.nextInt();
        campInformation.setTotalCampCommitteeSlots(campCommSlots);
        campInformation.setCurrentCampCommitteeSlots(campCommSlots);
        scanner.nextLine();

        System.out.println("Enter the Camp Description: ");
        campInformation.setDescription(scanner.nextLine());

        //System.out.println("Enter the staff in charge "); //staff in charge is the staff that created the camp
        //campInformation.setStaffInCharge(scanner.nextLine());
    
        return campInformation;
    }

}
