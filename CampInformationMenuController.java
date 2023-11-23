import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CampInformationMenuController {
    Scanner scanner = Main.getScanner();

    public void CampInformationMenuControl(CampInformation campInfo, CampList campList) {
        CampInformationMenu campInfoMenu = new CampInformationMenu();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        CampNameComparator comparator = new CampNameComparator();
        int choice;
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
                    System.out.print("Current camp starting date: ");
                    System.out.println(campInfo.getStartingDate());
                    System.out.println("Enter new camp starting date: ");
                    String startingDateUnformatted = scanner.nextLine();
                    LocalDate startingDate = LocalDate.parse(startingDateUnformatted, formatter);
                    campInfo.setStartingDate(startingDate);
                    break;
                case 4:
                    System.out.print("Current camp ending date: ");
                    System.out.println(campInfo.getStartingDate());
                    System.out.println("Enter new camp ending date: ");
                    String endingDateUnformatted = scanner.nextLine();
                    LocalDate endingDate = LocalDate.parse(endingDateUnformatted, formatter);
                    campInfo.setEndingDate(endingDate);
                    break;
                case 5:
                    System.out.print("Current camp registration deadline: ");
                    System.out.println(campInfo.getStartingDate());
                    System.out.println("Enter new camp registration deadline: ");
                    String registrationClosingDateUnformatted = scanner.nextLine();
                    LocalDate registrationClosingDate = LocalDate.parse(registrationClosingDateUnformatted, formatter);
                    campInfo.setRegistrationClosingDate(registrationClosingDate);
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
}