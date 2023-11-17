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
    }

    public CampList getCampsRegisteredAsParticipant(){  return CampsRegisteredAsParticipant;    }


    public boolean viewAvailableCamps(CampList campList){
        if(campList.list.size() == 0){
                System.out.println("There are no camps at all");
                return false;
            }
        for(int i = 0; i < campList.list.size(); i++){
            if(((Camp) campList.list.get(i)).getCampInfo().getFaculty() == Faculty.NTU && ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots() != 0){ // NTU-wide
                    System.out.println("Camp Name: " + ((Camp) campList.getFromList(i)).getCampInfo().getCampName());
                    System.out.println("Camp Vacancy: " + ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots());
                    continue;
            }
            if(((Camp) campList.list.get(i)).getCampInfo().getFaculty() != Faculty.NTU && super.getFacultyInformation() == ((Camp) campList.getFromList(i)).getCampInfo().getFaculty() && ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots() != 0){ // faculty-specific
                System.out.println("Camp Name: " + ((Camp) campList.getFromList(i)).getCampInfo().getCampName());
                System.out.println("Camp Vacancy: " + ((Camp) campList.getFromList(i)).getCampInfo().getCurrentParticipantSlots());
                continue;
            }
        }
        return false;
        }
            

    public void viewRegisteredCamps() {
        for (int i=0; i<CampsRegisteredAsParticipant.list.size(); i++){
            Camp camp = (Camp) CampsRegisteredAsParticipant.getFromList(i);
            camp.printCampInfo();
        } 
    }

    public void registerCampAsAttendee(Camp camp){
        if (camp.getCampInfo().getCurrentParticipantSlots() !=0 && checkCampDeadline(camp) && !checkCampClash(camp) && checkWithdraw(camp)){ //check vacancy, check date clash, check ddl, check whether withdrawed
            camp.getCampAttendeesList().addToList(this);
            CampsRegisteredAsParticipant.addToList(camp);
        }
        else{
            System.out.println("You can't register for this camp.");
        }
    }
    
    public void registerCampAsCampComm(Camp camp){
        if (camp.getCampInfo().getCurrentCampCommitteeSlots()!=0 && checkCampDeadline(camp) && !checkCampClash(camp) && checkWithdraw(camp)){ //check vacancy, check date clash, check ddl, check whether is camp comm, check whether withdrawed
            camp.getCampCommitteeMembersList().addToList(this);
            int newNum = camp.getCampInfo().getCurrentCampCommitteeSlots()-1;   //vacancy drops
            camp.getCampInfo().setCurrentCampCommitteeSlots(newNum);
            newNum = camp.getCampInfo().getCurrentCampCommitteeSlots()-1;
            camp.getCampInfo().setCurrentCampMemberSlots(newNum);
            campCommMember = new CampCommMember(super.getName(), super.getUserID(), super.getPassword(), super.getFacultyInformation(), camp);
        }
        else{
            System.out.println("You can't register for this camp.");
        }
    }

    public void withdrawCamp(Camp camp){
        if (camp.getCampAttendeesList().list.contains(this)){
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
        }
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
        for (int i=0; i<CampsWithdrawnFrom.list.size(); i++){
            Camp cmp = (Camp) CampsWithdrawnFrom.list.get(i);
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