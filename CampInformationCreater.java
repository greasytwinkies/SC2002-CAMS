import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

        LocalDate currentDate = LocalDate.now();
        boolean validDate = false;
        LocalDate registrationClosingDate = currentDate ;
        do {
            try {
                System.out.println("Enter the Camp Registration Closing Date: ");
                String registrationClosingDateUnformatted = scanner.nextLine();
                registrationClosingDate = LocalDate.parse(registrationClosingDateUnformatted, formatter);
            }
            catch (DateTimeParseException e) {
                System.out.println("Please enter a date in the format of DD-MM-YYYY.");
                continue;
            }
            if (registrationClosingDate.isAfter(currentDate) || registrationClosingDate.isEqual(currentDate)) { validDate = true; break; }
            System.out.println("Camp registration closing date cannot be before current date! (Current date: " + currentDate + ")");
        } while (validDate == false);
        campInformation.setRegistrationClosingDate(registrationClosingDate);


        // make sure registration closing date is after current date

        validDate = false;
        LocalDate startingDate = registrationClosingDate;
        do {
            try {
                System.out.println("Enter the Camp Starting Date: ");
                String startingDateUnformatted = scanner.nextLine();
                startingDate = LocalDate.parse(startingDateUnformatted, formatter);
            }
            catch (DateTimeParseException e) {
                System.out.println("Please enter a date in the format of DD-MM-YYYY.");
                continue;
            }
            if (startingDate.isAfter(registrationClosingDate) || startingDate.isEqual(registrationClosingDate)) { validDate = true; break; }
            System.out.println("Camp starting date cannot be before registration closing date!");
        } while (validDate == false);
        campInformation.setStartingDate(startingDate);

        // make sure camp starting date is after camp registration closing date

        validDate = false;
        LocalDate endingDate = startingDate;
        do {
            try {
                System.out.println("Enter the Camp Ending Date: ");
                String endingDateUnformatted = scanner.nextLine();
                endingDate = LocalDate.parse(endingDateUnformatted, formatter);
            }
            catch (DateTimeParseException e) {
                System.out.println("Please enter a date in the format of DD-MM-YYYY.");
                continue;
            }
            if (endingDate.isAfter(startingDate) || endingDate.isEqual(startingDate)) { validDate = true; break; }
            System.out.println("Camp ending date cannot be before starting date!");
        } while (validDate == false);
        campInformation.setEndingDate(endingDate);

        // make sure camp ending date is same date or later than camp ending date

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
        campInformation.setCurrentCampMemberSlots(campInformation.getCurrentCampCommitteeSlots()+campInformation.getCurrentParticipantSlots());
        scanner.nextLine();

        System.out.println("Enter the Camp Description: ");
        campInformation.setDescription(scanner.nextLine());

        //System.out.println("Enter the staff in charge "); //staff in charge is the staff that created the camp
        //campInformation.setStaffInCharge(scanner.nextLine());
    
        return campInformation;
    }

}