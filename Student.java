import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *  Represents a student object within the CAMS System, extends User class functionality
 */

public class Student extends User
{

    /**
     *  A Camp Committee member object
     */
    private CampCommMember campCommMember;

     /**
     *  scanner object for receiving inputs
     */
    Scanner scanner = new Scanner(System.in);

    /**
     *  Date time formatter for formatting specific calender dates
     */
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    /**
     *  now refers to the current date 
     */
    LocalDateTime now = LocalDateTime.now();

    /**
     *  An EnquiryMenuController object, for student objects to access enquiries
     */
    EnquiryMenuController enquiryMenuController = new EnquiryMenuController(this);

    
    /**
     * Creates a student object with a name, userID, password, and faculty. Inherits from User
     * @param name This is the name of the Student
     * @param userID This is the students userID
     * @param password This is the students password to access CAMS
     * @param facultyInformation This is the students faculty that they are a part of
     */
    public Student(String name, String userID, String password, Faculty facultyInformation) {
        super(name, userID, password, facultyInformation);
    }

    /**
     * Method to view all available camps to a Student, through checking for vacancies, faculty, and visibility
     * @param campList The main camplist of all camps
     * @return a camplist of available camps to students
     */
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

    /**
     * Method to return all available camps that a student is eligible for.
     * @param campList The main camplist of all camps
     * @return camplist That a student may take part in
     */
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


    /**
     * Method for a student to view all the camps they have registered for (as a camp committee member, or as a participant)
     * @param campList A list of all camps
     */
    public void viewRegisteredCamps(CampList campList) {
        System.out.println("Camps Registered as Participant:");
        campList.printUserCamp(this);
    }

    /**
     * Method for a Student to register for a camp as a participant
     * @param camp The camp that a student wants to register for
     */
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
    
    /**
     * Method for a Student to register for a camp as a Camp Committee Member
     * @param camp The camp that the student wants to register for
     */
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


    /**
     * Method that returns a Camp Committee Member object
     * @return The Camp Committee Member object
     */
    public CampCommMember getCampComm(){
        return this.campCommMember;
    }

    /**
     * Method to set a indicate that a student is a Camp Committee Member
     * @param campCommMember Set a Student as a Camp Committee object
     */
    public void setCampCommMember(CampCommMember campCommMember){
        this.campCommMember = campCommMember;
    }

    /**
     * Method to withdraw a student from a camp. It checks if student is registered, before withdrawal.
     * @param camp The camp that the student is a member of
     * @param campList The main camp List
     * @return True if successfully withdrawn, else false.
     */
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


    /**
     * Method for checking if a Student has withdrawn from a camp in the past.
     * @param camp The camp that is being checked for student withdrawal
     * @return False if a student has been withdrawn from the camp before, else true.
     */
    public boolean checkWithdraw(Camp camp){
        ArrayList<Object> withdrawn = camp.getWithdrawnStudentList().returnStudentList();
        for (Object item : withdrawn){
            Student student = (Student) item;
            if (student.getUserID().equals(this.getUserID())) {return false;}   //got withdraw before
        }
        return true; //nvr withdraw before
    }

    /**
     * Method to check a camps registration deadline
     * @param camp The camp being checked for its deadline
     * @return True if current date is before camp deadline
     */
    public boolean checkCampDeadline(Camp camp){
        LocalDate curDate = LocalDate.now();
        LocalDate regDdl = camp.getCampInfo().getRegistrationClosingDate();
        if (curDate.isBefore(regDdl)){ return true;}
        else{   return false;}
    }

    /**
     * Method to check is a students camp registration clashes with any other camps they have registered for
     * @param camp Main camp a student is registering for
     * @param campList List of Camps that a student has also registered for
     * @return True if there is a clash, False otherwise.
     */
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


    /**
     * Method to deetermine if a student has registered for a specific camp, as a committee member, or as a participant
     * @param camp Camp that they have registered for
     * @param campList To cross check with all other camps student has registered for as a participant
     * @return True if Student has registered for the camp, false otherwise
     */
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