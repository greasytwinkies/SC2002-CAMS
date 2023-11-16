import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CampInformationCreater {
    public static CampInformation populateCampInformation(){
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

        System.out.println("Enter the faculty: ");
        //campInformation.setFaculty(null);

        System.out.println("Enter the Camp Location ");
        String location = scanner.nextLine();

        System.out.println("Enter the total number of Camp Participant Slots: ");
        campInformation.setTotalParticipantSlots(scanner.nextInt());
        campInformation.setCurrentParticipantSlots(0);

    
        System.out.println("Enter the total number of camp Comittee Slots: ");
        campInformation.setTotalCampCommitteeSlots(scanner.nextInt());
        campInformation.setCurrentCampCommitteeSlots(0);
        scanner.nextLine();

        System.out.println("Enter the Camp Description: ");
        campInformation.setDescription(scanner.nextLine());

        System.out.println("Enter the staff in charge ");
        campInformation.setStaffInCharge(scanner.nextLine());
    
        return campInformation;
    }

}
