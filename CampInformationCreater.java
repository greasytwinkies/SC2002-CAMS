import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CampInformationCreater {
    public static CampInformation populateCampInformation(Staff staff, CampList campList){
        Scanner scanner = Main.getScanner();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        CampInformation campInformation = new CampInformation();
        CampNameComparator comparator = new CampNameComparator();


        System.out.println("Do you want your camp to be visible?: (true/false)");
        campInformation.setCampVisibility(scanner.nextBoolean());
        scanner.nextLine();

        while(true) {
            System.out.println("Enter the Camp Name: ");
            String campName = scanner.nextLine();
            Camp camp = campList.findCamp(campList, campName);
            if (camp==null){
                campInformation.setCampName(campName);
                campList.updateList(comparator);
                break;
            }
            System.out.println("There's an existing camp with the same name.");        
        }

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
        int campCommSlots;
        do{
            campCommSlots  = scanner.nextInt();
            if(campCommSlots >10){
                System.out.println("Maximum number of camp committee allowed is 10. Please re-enter.");
            }
        }while(campCommSlots >10);
        
        campInformation.setTotalCampCommitteeSlots(campCommSlots);
        campInformation.setCurrentCampCommitteeSlots(campCommSlots);
        campInformation.setCurrentCampMemberSlots(campInformation.getCurrentCampCommitteeSlots()+campInformation.getCurrentParticipantSlots());
        scanner.nextLine();

        System.out.println("Enter the Camp Description: ");
        campInformation.setDescription(scanner.nextLine());

        //System.out.println("Enter the staff in charge "); //staff in charge is the staff that created the camp
        //campInformation.setStaffInCharge(scanner.nextLine());
    
        return campInformation;
    }

}