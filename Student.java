import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Student extends User
{
    private CampCommMember campCommMember;
    Scanner scanner = new Scanner(System.in);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime now = LocalDateTime.now();

    EnquiryMenuController enquiryMenuController = new EnquiryMenuController(this);

    public Student(String name, String userID, String password, Faculty facultyInformation) {
        super(name, userID, password, facultyInformation);
    }


    public CampList viewAvailableCamps(CampList campList){
        CampList availableCamps = new CampList("Camps available to student");
        if(campList.list.size() == 0){
                System.out.println("There are currently no available camps!");
                return null;
            }
        for(int i = 0; i < campList.list.size(); i++){
            Camp camp = (Camp)campList.list.get(i);
            Faculty campFaculty = camp.getCampInfo().getFaculty();
            int campVacancies = camp.getCampInfo().getCurrentCampMemberSlots();
            boolean campVisibility = camp.getCampInfo().getCampVisibility();
            boolean withdrawn = checkWithdraw(camp);

            // TODO: need to extend this code for visibility later
            // TODO: also need to check camp dates, etc. (DONE) 
            // TODO: include camp comm member vacancies. (DONE)
            // TODO: check if camp already registered for. (DONE)
            // TODO: might have to split this function, it is getting too big
            // first, check if camp has vacancies (DONE)
            if (campVacancies > 0 && campVisibility && withdrawn) {
                // now check faculty of camp - two scenarios: school-wide camp or faculty-camp (must match with faculty of user)
                if ((campFaculty == Faculty.NTU) || super.getFacultyInformation() == campFaculty) {
                    if(checkCampDeadline(camp) && !isCampRegistered(camp,campList) && !checkCampClash(camp,campList)){ // if present date is before the registration closing date & camps not registered yet
                        availableCamps.addToList(camp);
                        continue;
                    }
                }
            }
        }
        if (availableCamps.list.size() == 0) {
            System.out.println("There are currently no available camps!");
            return null;
        }
        // now print all available camps
        // TODO: print camp faculty?
        for (int x = 0; x < availableCamps.list.size(); x++) {
            Camp availableCamp = (Camp) availableCamps.list.get(x);
            int vacancies = availableCamp.getCampInfo().getCurrentParticipantSlots();
            int campCommVacancies = availableCamp.getCampInfo().getCurrentCampCommitteeSlots();
            System.out.print(x+1 + ") ");
            System.out.println(availableCamp.getCampInfo().getCampName() + "\t\t(" + vacancies + "/" + campCommVacancies + ")");
        }
        
        return availableCamps;

    }

    public CampList returnAvailableCamps(CampList campList){
        CampList availableCamps = new CampList("Camps available to student");
        if(campList.list.size() == 0){
                System.out.println("There are currently no available camps!");
                return null;
            }
        for(int i = 0; i < campList.list.size(); i++){
            Camp camp = (Camp)campList.list.get(i);
            Faculty campFaculty = camp.getCampInfo().getFaculty();
            int campVacancies = camp.getCampInfo().getCurrentCampMemberSlots();
            boolean campVisibility = camp.getCampInfo().getCampVisibility();
            boolean withdrawn = checkWithdraw(camp);
            if (campVacancies > 0 && campVisibility && withdrawn) {
                // now check faculty of camp - two scenarios: school-wide camp or faculty-camp (must match with faculty of user)
                if ((campFaculty == Faculty.NTU) || super.getFacultyInformation() == campFaculty) {
                    if(checkCampDeadline(camp) && !isCampRegistered(camp,campList) && !checkCampClash(camp,campList)){ // if present date is before the registration closing date & camps not registered yet
                        availableCamps.addToList(camp);
                        continue;
                    }
                }
            }
        }
        if (availableCamps.list.size() == 0) {
            System.out.println("There are currently no available camps!");
            return null;
        }
        
        return availableCamps;

    }


    public void viewRegisteredCamps(CampList campList) {
        System.out.println("Camps Registered as Participant:");
        campList.printUserCamp(this);
    }

    public void registerCampAsAttendee(Camp camp){
        // do all the checking in the availableCamps function rather than here
        if (camp.getCampInfo().getCurrentParticipantSlots()>0){
            camp.getCampAttendeesList().addToList(this);
            camp.getCampMembersList().addToList(this);
            // getCampsRegisteredAsParticipant().addToList(camp);
            camp.getCampInfo().setCurrentParticipantSlots(camp.getCampInfo().getCurrentParticipantSlots()-1);
            camp.getCampInfo().setCurrentCampMemberSlots(camp.getCampInfo().getCurrentCampMemberSlots()-1);
            System.out.println("Successfully registered for " + camp.getCampInfo().getCampName() + " as PARTICIPANT!");
        }
        else{System.out.println("The camp is full!");}
    }
    
    public void registerCampAsCampComm(Camp camp){
        if (camp.getCampInfo().getCurrentCampCommitteeSlots()>0){
            camp.getCampCommitteeMembersList().addToList(this);
            camp.getCampMembersList().addToList(this);
            camp.getCampInfo().setCurrentCampCommitteeSlots(camp.getCampInfo().getTotalCampCommitteeSlots()-1);
            camp.getCampInfo().setCurrentCampMemberSlots(camp.getCampInfo().getCurrentCampMemberSlots()-1);
            CampCommMember campCommMember = new CampCommMember(camp);
            this.setCampCommMember(campCommMember);
            System.out.println("Successfully registered for " + camp.getCampInfo().getCampName() + " as CAMP COMMITTEE MEMBER !");
        }
        else{System.out.println("The camp is full!");}
    }


    public CampCommMember getCampComm(){
        return this.campCommMember;
    }

    public void setCampCommMember(CampCommMember campCommMember){
        this.campCommMember = campCommMember;
    }

    public boolean withdrawCamp(Camp camp, CampList campList){
        if (isCampRegistered(camp, campList)){
            camp.getCampAttendeesList().list.remove(this);
            camp.getCampMembersList().deleteFromList(this);
            camp.getWithdrawnStudentList().addToList(this);
            camp.getCampInfo().setCurrentParticipantSlots(camp.getCampInfo().getCurrentParticipantSlots()+1);
            camp.getCampInfo().setCurrentCampMemberSlots(camp.getCampInfo().getCurrentCampMemberSlots()+1);
            return true;
        }
        return false;
    }


    public boolean checkWithdraw(Camp camp){
        ArrayList<Object> withdrawn = camp.getWithdrawnStudentList().returnStudentList();
        for (Object item : withdrawn){
            Student student = (Student) item;
            if (student.getUserID().equals(this.getUserID())) {return false;}   //got withdraw before
        }
        return true; //nvr withdraw before
    }

    public boolean checkCampDeadline(Camp camp){
        LocalDate curDate = LocalDate.now();
        LocalDate regDdl = camp.getCampInfo().getRegistrationClosingDate();
        if (curDate.isBefore(regDdl)){ return true;}
        else{   return false;}
    }

    public boolean checkCampClash(Camp camp, CampList campList) {
        // Get the start and end dates of the new camp
        LocalDate newCampStartDate = camp.getCampInfo().getStartingDate();
        LocalDate newCampEndDate = camp.getCampInfo().getEndingDate();
        CampList CampsRegisteredAsParticipant = campList.returnUserCamps(this);

        if(CampsRegisteredAsParticipant==null){return false;}
        
        // Check against all camps that the student is already registered for
        for (int i = 0; i < CampsRegisteredAsParticipant.list.size(); i++) {
            Camp registeredCamp = (Camp) CampsRegisteredAsParticipant.getFromList(i);
            LocalDate registeredCampStartDate = registeredCamp.getCampInfo().getStartingDate();
            LocalDate registeredCampEndDate = registeredCamp.getCampInfo().getEndingDate();
            
            // Check for any overlap between the new camp and the registered camp
            boolean startsDuringAnotherCamp = !newCampStartDate.isAfter(registeredCampEndDate) && !newCampStartDate.isBefore(registeredCampStartDate);
            boolean endsDuringAnotherCamp = !newCampEndDate.isAfter(registeredCampEndDate) && !newCampEndDate.isBefore(registeredCampStartDate);
            boolean coversAnotherCamp = newCampStartDate.isBefore(registeredCampStartDate) && newCampEndDate.isAfter(registeredCampEndDate);
    
            if (startsDuringAnotherCamp || endsDuringAnotherCamp || coversAnotherCamp) {
                return true; // There is a clash
            }
        }
        
        return false; // No clash with any registered camp
    }

    public boolean isCampRegistered(Camp camp, CampList campList){
        //as a committee
        ArrayList<Object> committeelist = camp.getCampCommitteeMembersList().returnStudentList();
        for (Object item : committeelist){
            Student student = (Student) item;
            if (student.getUserID().equals(this.getUserID())) {return true;}   //got withdraw before
        }
        //as a participant
        CampList campsRegistered = campList.returnUserCamps(this);
        if (campsRegistered==null){return false;}
        if (campsRegistered.findCamp(campsRegistered, camp.getCampInfo().getCampName())==null){
            return false;
        }
        return true;
    }

}