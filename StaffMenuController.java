import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
    Controls the interaction between Staff and StaffMenu classes.
*/
public class StaffMenuController {
    /**
    * Scanner to read inputs
    */
    Scanner scanner = Main.getScanner();

    /**
    * The main function for interaction between Staff and StaffMenu classes.
    * @param staff The staff interacting with the Staff menu.
    * @param campList The global list of camps.
    * @return The logout status of the given staff.
    * @throws FileNotFoundException When a given file cannot be found.
    */
    public int StaffMenuControl(Staff staff, CampList campList) throws FileNotFoundException {
        StaffMenu staffMenu = new StaffMenu();
        CampInformationMenuController campInfoControl = new CampInformationMenuController();

        int choice=0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Camp camp;
        do {
            staffMenu.printMenu();
            int valid=0;
            while (valid ==0){
                try {
                    choice = Integer.valueOf(scanner.nextLine());  
                    valid=1;                  
                } catch (Exception NumberFormatException) {
                    System.out.println("Please enter again!");
                }
            }
            switch (choice) {
                case 1:
                    CampInformation campInformation = CampInformationCreater.populateCampInformation(staff, campList);
                    camp = new Camp(campInformation, staff);
                    campList.addToList(camp);
                    campList.printList();
                    // add to staff's list of camps
                    break;
                case 2:
                    CampList userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    campList.printUserCamp(staff);
                    System.out.println("Enter the index of the camp you want to edit: ");
                    int option = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(option-1);
                    // if there are students or camp comm members, not supposed to edit
                    if(camp.getCampAttendeesList().list.size() == 0 && camp.getCampCommitteeMembersList().list.size() == 0){
                        campInfoControl.CampInformationMenuControl(camp.getCampInfo(), campList);
                    }else{
                        System.out.println("Cannot edit as there are already people inside camp.");
                        break;
                    }
                    break;
                case 3:
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    campList.printUserCamp(staff);
                    System.out.println("Enter the index of the camp you want to delete: ");
                    option = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(option-1);
                    if(camp.getCampMembersList().list.size() > 0){
                        System.out.println("Cannot delete because camp has people!");
                        break;
                    }
                    boolean done = campList.deleteFromList(camp);
                    if (done){ System.out.println("Successfully deleted");}
                    // delete from staff's list of camps too
                    break;
                case 4:
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    campList.printUserCamp(staff);
                    System.out.println("Enter the index of the camp to toggle visibility of: ");
                    option = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(option-1);
                    campInfoControl.toggleCampVisibility(camp.getCampInfo());
                    break;
                case 5:
                    CampList allCamps = campList;
                    int counter=0;
                    System.out.println("Filter camps by: 1) No filter 2) Date 3) Location 4) Faculty");
                    option = Integer.valueOf(scanner.nextLine());
                    switch (option) {
                        case 1:
                            allCamps.printList(); 
                            break;
                        case 2:
                            valid = 0;
                            int month=0;
                            while(valid==0){
                                System.out.println("Enter month of camp start date:");
                                try{
                                    month = Integer.valueOf(scanner.nextLine());
                                    valid=1;                                    
                                } catch (Exception NumberFormatException) {
                                    System.out.println("Please enter the integer form of the month");
                                }
                            }
                                                        
                            for (int i=0; i<allCamps.list.size(); i++) {
                                Camp camp1 = (Camp) allCamps.list.get(i);
                                if (camp1.getCampInfo().getStartingDate().getMonthValue() == month) { 
                                    counter++;
                                    System.out.println(counter + ") " + camp1.getCampInfo().getCampName());
                                }
                            }
                            if (counter == 0) { System.out.println("There are no camps that start in this month.");} 
                            break;
                        case 3:
                            System.out.println("Enter location of camp: ");
                            String location = scanner.nextLine();
                            for (int i=0; i<allCamps.list.size(); i++) {
                                Camp camp1 = (Camp) allCamps.list.get(i);
                                if (camp1.getCampInfo().getLocation().equals(location)) { 
                                    counter++;
                                    System.out.println(counter + ") " + camp1.getCampInfo().getCampName());
                                }
                            }
                            if (counter == 0) { System.out.println("There are no camps at the specified location.");} 
                            break;
                        case 4:
                            System.out.println("Enter faculty of camp: ");
                            Faculty faculty = Faculty.valueOf(scanner.nextLine().toUpperCase());
                            for (int i=0; i<allCamps.list.size(); i++) {
                                Camp camp1 = (Camp) allCamps.list.get(i);
                                if (camp1.getCampInfo().getFaculty().equals(faculty)) { 
                                    counter++;
                                    System.out.println(counter + ") " + camp1.getCampInfo().getCampName());
                                }
                            }
                            if (counter == 0) { System.out.println("There are no camps under the specified faculty.");} 
                            break;
                    }
                    // filters: dates, location, faculty
                    break;
                case 6:
                    campList.printUserCamp(staff);
                    break;
                case 7:
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    campList.printUserCamp(staff);
                    System.out.println("Enter the index of the camp you want to view enquiry of:");
                    option = Integer.valueOf(scanner.nextLine());
                    try{
                        camp = (Camp) userCamps.getFromList(option-1);
                        if(camp.getEnquiries() == null){
                            break;
                        }else{
                            camp.getEnquiries().printList();
                        }
                        // System.out.println("Error debugging: (3)");
                    }
                    catch (NullPointerException e){
                        System.out.println("This camp does not exist!");
                    }
                    break;
                case 8:
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    campList.printUserCamp(staff);
                    System.out.println("Enter the index of the camp you want to reply enquiry to:");
                    option = Integer.valueOf(scanner.nextLine());
                    try{
                        camp = (Camp) userCamps.getFromList(option-1);
                        camp.getEnquiries().replyEnquiries();
                    }
                    catch(NullPointerException e){
                        System.out.println("This camp does not exist!");
                    }
                    break;
                /* where code modification starts */
                case 9: // view camp suggestions
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    campList.printUserCamp(staff);
                    System.out.println("Enter the index of the camp you want to view suggestions of:");
                    option = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(option-1);
                    camp.getSuggestions().printList();
                    break;
                case 10: // approve camp suggestions
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    campList.printUserCamp(staff);
                    System.out.println("Enter the index of the camp you want to view suggestions of:");
                    option = Integer.valueOf(scanner.nextLine());
                    camp = (Camp) userCamps.getFromList(option-1);
                    camp.getSuggestions().approveSuggestions();
                    break;
                case 11: // student report
                    // first, prompt user if they want to print student report for 1) all your created camps 2) a specific camp
                    // next, prompt user for micro-level filtering: 1) all attendees 2) participants only 3) camp comm only 4) specific individual.
                    StudentReport studentReport = new StudentReport();
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    System.out.println("Print student report for 1) All your created camps or 2) A specific camp\n");
                    int campFilter = Integer.valueOf(scanner.nextLine());
                    if (campFilter == 1) { // print all camps
                        studentReport.printReportsForAllCamps(userCamps);
                    }
                    else if (campFilter == 2) { // print one camp only
                        campList.printUserCamp(staff);
                        userCamps = campList.returnUserCamps(staff);
                        System.out.println("Enter index of camp you want to generate a student report for:\n");
                        option = Integer.valueOf(scanner.nextLine());
                        camp = (Camp) userCamps.getFromList(option-1);
                        studentReport.printReport(camp);
                    }
                    System.out.println("Student reports printed to studentReport.txt");
                    break;
                case 12:
                    userCamps = campList.returnUserCamps(staff);
                    if (userCamps == null) { System.out.println("You have not created any camps."); break;}
                    System.out.println("Print camp committee performance report for 1) All your camps or 2) A specific camp\n");
                    option = Integer.valueOf(scanner.nextLine());
                    if (option == 1) {
                        System.out.println("Printing Camp Committee Member performance report for ALL your camps:\n");
                        for (int i=0; i< userCamps.list.size(); i++) {
                            camp = (Camp) userCamps.getFromList(i);
                            System.out.println(camp.getCampInfo().getCampName() + ":");
                            CampCommitteeReport campCommitteeReport = new CampCommitteeReport();
                            campCommitteeReport.printReport(camp); 
                        }
                    }
                    if (option == 2) {
                        campList.printUserCamp(staff);
                        System.out.println("Enter the index of the camp you want to generate a committee member report for:\n");
                        option = Integer.valueOf(scanner.nextLine());
                        camp = (Camp) userCamps.getFromList(option-1);
                        System.out.println("Printing Camp Committee Member Report for " + camp.getCampInfo().getCampName() + ":");
                        CampCommitteeReport campCommitteeReport = new CampCommitteeReport();
                        campCommitteeReport.printReport(camp);
                    }
                    break;
                case 13:
                    LoginPage.changePassword(staff);
                    System.out.println("Please log in again.");
                    LoginPage.Logout();
                    break;
                    
            }
            System.out.println();
            System.out.println();
            System.out.println();
        } while ((choice > 0 && choice < 14) && LoginPage.getLogout()==0);

        return LoginPage.Logout();

    }
}