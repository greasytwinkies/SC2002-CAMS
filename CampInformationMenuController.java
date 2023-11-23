import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CampInformationMenuController {
    Scanner scanner = Main.getScanner();

    public void CampInformationMenuControl(CampInformation campInfo, CampList campList) {
        CampInformationMenu campInfoMenu = new CampInformationMenu();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        CampNameComparator comparator = new CampNameComparator();
        LocalDate currentDate = LocalDate.now();
        int choice;
        boolean validDate = false;
        do {
            campInfoMenu.printMenu();
            choice = Integer.valueOf(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.print("Current visibility status: ");
                    if (campInfo.getCampVisibility()) {
                        System.out.println("Visible");
                    }
                    else {
                        System.out.println("Hidden");
                    }
                    System.out.println("Enter Y/N (True/False) for visibility status");
                    String input = scanner.nextLine();
                    
                    if(input.toUpperCase().equals("Y")){
                        campInfo.setCampVisibility(true);
                    }else if(input.toUpperCase().equals("N")){
                        campInfo.setCampVisibility(false);
                    }
                    break;
                case 2:
                    System.out.print("Current camp name: ");
                    System.out.println(campInfo.getCampName());
                    while(true) {
                        System.out.println("Enter new camp name: ");
                        String newCampName = scanner.nextLine();
                        Camp camp = campList.findCamp(campList, newCampName);
                        if (camp==null){
                            campInfo.setCampName(newCampName);
                            campList.updateList(comparator);
                            break;
                        }
                        System.out.println("There's an existing camp with the same name.");        
                    }
                    break;
                case 3:
                    System.out.print("Current camp registration deadline: ");
                    System.out.println(campInfo.getRegistrationClosingDate() + "\n");
                    System.out.println("Enter new camp registration deadline: ");
                    LocalDate registrationClosingDate = campInfo.getRegistrationClosingDate();
                    validDate = false;
                    do {
                        try {
                            String registrationClosingDateUnformatted = scanner.nextLine();
                            registrationClosingDate = LocalDate.parse(registrationClosingDateUnformatted, formatter);
                        }
                        catch (DateTimeParseException e) {
                            System.out.println("Please enter a date in the format DD-MM-YYYY");
                            continue;
                        }
                        // registration closing date cannot be BEFORE currentDate, cannot be after starting and ending date
                        if (checkDate(registrationClosingDate, currentDate) && checkDate(campInfo.getStartingDate(), registrationClosingDate) && checkDate(campInfo.getEndingDate(), registrationClosingDate)) {
                            validDate = true;
                            System.out.println("Successfully changed camp registration deadline to " + registrationClosingDate);
                            break;
                        }
                        System.out.println("Registration closing date cannot be before current date and/or after camp starting date or camp ending date. Please try again.");
                        } while (validDate == false);
                    campInfo.setRegistrationClosingDate(registrationClosingDate);
                    break;
                case 4:
                    System.out.print("Current camp starting date: ");
                    System.out.println(campInfo.getStartingDate());
                    System.out.println("Enter new camp starting date: ");
                    LocalDate campStartingDate = campInfo.getStartingDate();
                    validDate = false;
                    do {
                        try {
                            String campStartingDateUnformatted = scanner.nextLine();
                            campStartingDate = LocalDate.parse(campStartingDateUnformatted, formatter);
                        }
                        catch (DateTimeParseException e) {
                            System.out.println("Please enter a date in the format DD-MM-YYYY");
                            continue;
                        }
                        // camp starting date cannot be before current date or campRegistrationClosingDate, must be before camp ending date.
                        if (checkDate(campStartingDate, currentDate) && checkDate(campStartingDate, campInfo.getRegistrationClosingDate()) && checkDate(campInfo.getEndingDate(), campStartingDate)) {
                            validDate = true;
                            System.out.println("Successfully changed camp starting date to " + campStartingDate);
                            break;
                        }
                        System.out.println("Camp starting date cannot be before current date/ camp registration closing date, and cannot be after camp ending date. Please try again.");
                        } while (validDate == false);
                    campInfo.setStartingDate(campStartingDate);
                    break;
                case 5:
                    System.out.print("Current camp ending date: ");
                    System.out.println(campInfo.getStartingDate());
                    System.out.println("Enter new camp ending date: ");
                    LocalDate campEndingDate = campInfo.getEndingDate();
                    validDate = false;
                    do {
                        try {
                            String campEndingDateUnformatted = scanner.nextLine();
                            campEndingDate = LocalDate.parse(campEndingDateUnformatted, formatter);
                        }
                        catch (DateTimeParseException e) {
                            System.out.println("Please enter a date in the format DD-MM-YYYY");
                            continue;
                        }
                        // camp ending date cannot be before any of the dates
                        if (checkDate(campEndingDate, currentDate) && checkDate(campEndingDate, campInfo.getRegistrationClosingDate()) && checkDate(campEndingDate, campInfo.getStartingDate())) {
                            validDate = true;
                            System.out.println("Successfully changed camp ending date to " + campEndingDate);
                            break;
                        }
                        System.out.println("Camp ending date cannot be before current date/ camp registration deadline/ camp starting date. Please try again.");
                        } while (validDate == false);
                    campInfo.setEndingDate(campEndingDate);
                    break;
                case 6:
                    // TODO camp user group
                    System.out.println("Camp user group (faculty or entire school): ");
                    break;
                case 7:
                    System.out.print("Current camp location: ");
                    System.out.println(campInfo.getCampName());
                    System.out.println("Enter new camp location: ");
                    String newCampLocation = scanner.nextLine();
                    campInfo.setLocation(newCampLocation);
                    break;
                case 8:
                    System.out.print("Current camp participant slots: ");
                    System.out.println(campInfo.getCurrentParticipantSlots());
                    System.out.println("Enter new number of participant slots: ");
                    int newCampParticipantSlots = Integer.valueOf(scanner.nextLine());
                    campInfo.setTotalParticipantSlots(newCampParticipantSlots);
                    break;
                case 9:
                    System.out.println("Current camp committee slots: " + campInfo.getCurrentParticipantSlots());
                    System.out.println("Enter new number of committee slots: ");
                    int newCampCommitteeSlots = Integer.valueOf(scanner.nextLine());
                    campInfo.setTotalParticipantSlots(newCampCommitteeSlots);
                    break;
                case 10:
                    System.out.print("Current camp description: ");
                    System.out.println(campInfo.getDescription());
                    System.out.println("Enter new camp description: ");
                    String newCampDescription = scanner.nextLine();
                    campInfo.setLocation(newCampDescription);
                    break;
                case 11:
                    break;
            }
        } while (choice > 0 && choice < 11);
    }

    public void toggleCampVisibility(CampInformation campinfo){
        
        System.out.print(campinfo.getCampName() + " Current visibility status: ");
        if (campinfo.getCampVisibility()) {
            System.out.println("Visible");
        }
        else {
            System.out.println("Hidden");
        }
        System.out.println("Enter Y/N (True/False) for visibility status");
        String input = scanner.nextLine();
        
        if(input.toUpperCase().equals("Y")){
            campinfo.setCampVisibility(true);
        }else if(input.toUpperCase().equals("N")){
            if(campinfo.getCurrentCampMemberSlots() == 0){
                campinfo.setCampVisibility(false);
            }
            else{
                System.out.println("Cannot set camp to hidden. Your camp has members");
            }
        }
        String visibility = Boolean.toString(campinfo.getCampVisibility());
        System.out.print(campinfo.getCampName() + " visibility status currently set as: " + visibility + "\n");
        

    }

    public boolean checkDate(LocalDate date1, LocalDate date2) {
        return (!(date1.isBefore(date2)));
    }
}