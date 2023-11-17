import java.util.Scanner;

import com.sun.tools.javac.Main;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Student extends User
{
    private CampList CampsRegisteredAsParticipant; 
    private CampList CampsWithdrawnFrom;
    private CampCommMember campCommMember;
    // private CampCommMember campCommMember;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime now = LocalDateTime.now();

    public Student(String name, String userID, String password, Faculty facultyInformation) {
        super(name, userID, password, facultyInformation);
        CampsRegisteredAsParticipant = new CampList("CampRegisteredList");
        CampsWithdrawnFrom = new CampList("Withdrawn Camps");
    }

    public CampList getCampsRegisteredAsParticipant(){  return CampsRegisteredAsParticipant;    }
    public CampList getCampsWithdrawnFrom(){  return CampsWithdrawnFrom;    }


    public CampList viewAvailableCamps(CampList campList){
        CampList availableCamps = new CampList("Camps available to student");
        if(campList.list.size() == 0){
                System.out.println("There are currently no available camps!");
                return null;
            }
        for(int i = 0; i < campList.list.size(); i++){
            Camp camp = (Camp)campList.list.get(i);
            Faculty campFaculty = camp.getCampInfo().getFaculty();
            int campVacancies = camp.getCampInfo().getCurrentParticipantSlots();

            // TODO: need to extend this code for visibility later
            // TODO: also need to check camp dates, etc.
            // TODO: include camp comm member vacancies.
            // TODO: check if camp already registered for.
            // TODO: might have to split this function, it is getting too big
            // first, check if camp has vacancies
            if (campVacancies > 0) {
                // now check faculty of camp - two scenarios: school-wide camp or faculty-camp (must match with faculty of user)
                if ((campFaculty == Faculty.NTU) || super.getFacultyInformation() == campFaculty) {
                    availableCamps.addToList(camp);
                    continue;
                }
            }
/*             if(((Camp) campList.list.get(i)).getCampInfo().getFaculty() == Faculty.NTU && ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots() != 0){ // NTU-wide
                    System.out.println("Camp Name: " + ((Camp) campList.getFromList(i)).getCampInfo().getCampName());
                    System.out.println("Camp Vacancy: " + ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots());
                    continue;
            }
            if(((Camp) campList.list.get(i)).getCampInfo().getFaculty() != Faculty.NTU && super.getFacultyInformation() == ((Camp) campList.getFromList(i)).getCampInfo().getFaculty() && ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots() != 0){ // faculty-specific
                System.out.println("Camp Name: " + ((Camp) campList.getFromList(i)).getCampInfo().getCampName());
                System.out.println("Camp Vacancy: " + ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots());
                continue;
            } */
        }
        if (availableCamps.list.size() == 0) {
            return null;
        }
        // now print all available camps
        // TODO: print camp faculty?
        for (int x = 0; x < availableCamps.list.size(); x++) {
            Camp availableCamp = (Camp)availableCamps.list.get(x);
            int vacancies = availableCamp.getCampInfo().getCurrentParticipantSlots();
            System.out.print(x+1 + ") ");
            System.out.println(availableCamp.getCampInfo().getCampName() + " (" + vacancies + " vacancies)");
        }
        
        return availableCamps;

    }
            

    public void viewRegisteredCamps() {
        for (int i=0; i<getCampsRegisteredAsParticipant().list.size(); i++){
            Camp camp = (Camp) getCampsRegisteredAsParticipant().getFromList(i);
            System.out.print(i+1 + ") ");
            camp.printCampInfo();
        } 
    }

    public void registerCampAsAttendee(Camp camp){
        // do all the checking in the availableCamps function rather than here


        camp.getCampAttendeesList().addToList(this);
        getCampsRegisteredAsParticipant().addToList(camp);
        camp.getCampInfo().setCurrentParticipantSlots(camp.getCampInfo().getCurrentParticipantSlots()-1);
        System.out.println("Successfully registered for " + camp.getCampInfo().getCampName() + " as PARTICIPANT!");
/*         if (camp.getCampInfo().getCurrentParticipantSlots() !=0 && checkCampDeadline(camp) && !checkCampClash(camp) && checkWithdraw(camp)){ //check vacancy, check date clash, check ddl, check whether withdrawed
            camp.getCampAttendeesList().addToList(this);
            CampsRegisteredAsParticipant.addToList(camp);
        }
        else{
            System.out.println("You can't register for this camp."); */
        }
    
    public void registerCampAsCampComm(Camp camp){

        camp.getCampCommitteeMembersList().addToList(this);
        camp.getCampInfo().setCurrentParticipantSlots(camp.getCampInfo().getCurrentParticipantSlots()-1);
        System.out.println("Successfully registered for " + camp.getCampInfo().getCampName() + " as CAMP COMMITTEE MEMBER !");

         // TODO: actually add the camp to the campComm variable in Student class
/*         if (camp.getCampInfo().getCurrentCampCommitteeSlots()!=0 && checkCampDeadline(camp) && !checkCampClash(camp) && checkWithdraw(camp)){ //check vacancy, check date clash, check ddl, check whether is camp comm, check whether withdrawed
            camp.getCampCommitteeMembersList().addToList(this);
            int newNum = camp.getCampInfo().getCurrentCampCommitteeSlots()-1;   //vacancy drops
            camp.getCampInfo().setCurrentCampCommitteeSlots(newNum);
            newNum = camp.getCampInfo().getCurrentCampCommitteeSlots()-1;
            camp.getCampInfo().setCurrentCampMemberSlots(newNum);
            
        }
        else{
            System.out.println("You can't register for this camp.");
        } */
    }

    public void withdrawCamp(Camp camp){
        camp.getCampAttendeesList().list.remove(this);
        camp.getCampInfo().setCurrentParticipantSlots(camp.getCampInfo().getCurrentParticipantSlots()+1);
        getCampsRegisteredAsParticipant().list.remove(camp);
        getCampsWithdrawnFrom().list.add(camp);
        


/*         if (camp.getCampAttendeesList().list.contains(this)){
            camp.getCampAttendeesList().list.remove(this);
            
            int newNum = camp.getCampInfo().getCurrentCampMemberSlots()+1;
            camp.getCampInfo().setCurrentCampMemberSlots(newNum);
            
            newNum = camp.getCampInfo().getCurrentParticipantSlots()+1;
            camp.getCampInfo().setCurrentParticipantSlots(newNum);

            CampsRegisteredAsParticipant.list.remove(camp);

            CampsWithdrawnFrom.list.add(camp);
        }
        else{
            System.out.println("You are not registered to this camp");
        } */
    }

    public void submitEnquiry(Camp camp){
        Enquiry enquiry = new Enquiry(this);
        camp.getEnquiries().addToList(enquiry);
    }

    public void viewEnquiries(Camp camp) {
        camp.getEnquiries().printUserEnquiry(this);
    }

    public void editEnquiry(Camp camp) {
        camp.getEnquiries().printUserEnquiry(this);
        System.out.print("Entry to be edited: ");
        int idx = Integer.valueOf(scanner.nextLine());
        Enquiry enquiry = (Enquiry) camp.getEnquiries().getFromList(idx);
        if (enquiry.getReply().equals("")){
            camp.getEnquiries().editEnquiries(idx);
        }
        else{
            System.out.println("You cannot edit your entry");
        }
    }

    public void deleteEnquiry(Camp camp) {
        camp.getEnquiries().printUserEnquiry(this);
        System.out.print("Entry to be deleted: ");
        int idx = Integer.valueOf(scanner.nextLine());
        Enquiry enquiry = (Enquiry) camp.getEnquiries().getFromList(idx);
        if (enquiry.getReply().equals("")){
            camp.getEnquiries().deleteFromList(idx);
        }
        else{
            System.out.println("You cannot delete your entry");
        }
    }

    public boolean checkWithdraw(Camp camp){
        for (int i=0; i<getCampsWithdrawnFrom().list.size(); i++){
            Camp cmp = (Camp) getCampsWithdrawnFrom().list.get(i);
            if (cmp == camp) {return false;}   //got withdraw before
        }
        return true; //nvr withdraw before
    }

    public boolean checkCampDeadline(Camp camp){
        LocalDate curDate = LocalDate.now();
        LocalDate regDdl = camp.getCampInfo().getRegistrationClosingDate();
        if (curDate.isBefore(regDdl)){ return true;}
        else{   return false;}
    }

    public boolean checkCampClash(Camp camp){   //true means clash false means no clash
        LocalDate newCampStartDate = camp.getCampInfo().getStartingDate();
        LocalDate newCampEndDate = camp.getCampInfo().getEndingDate();
        for (int i=0; i<CampsRegisteredAsParticipant.list.size(); i++){
            Camp cmp = (Camp) CampsRegisteredAsParticipant.getFromList(i);
            LocalDate cmpStartDate = cmp.getCampInfo().getStartingDate();
            LocalDate cmpEndDate = cmp.getCampInfo().getEndingDate();
            if (newCampStartDate.isBefore(cmpEndDate) && newCampStartDate.isAfter(cmpStartDate)){   return true;}
            else if (newCampEndDate.isBefore(cmpEndDate) && newCampEndDate.isAfter(cmpStartDate)){  return true;}
            else if (newCampStartDate.isBefore(cmpStartDate) && newCampEndDate.isAfter(cmpEndDate)){    return true;}
        }
        return false;
    }

}