import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
    // private CampList CampsRegisteredAsStudent; //possible camplist class?
    // private CampList CampRegisteredAsCommitteeMember;
    // private boolean inCampComm;
    Scanner scanner = new Scanner(System.in);

    public Student(String name, String userID, String password, Faculty facultyInformation) {
        super(name, userID, password, facultyInformation);
        // CampsRegisteredAsStudent = new CampList(this.getUserID()+ "'s camps registered as a student");   //assuming that there's a constructor that allows u to pass in listName
        // CampRegisteredAsCommitteeMember = new CampList(this.getUserID()+ "'s camps registered as a camp commitee");
    }

    

    // public boolean getInCampComm(){ return this.inCampComm;}
    
    // public void setInCampComm(Boolean status, Camp camp){           //they can't quit :D so no need care abt status=0 
    //     this.inCampComm=status; 
    //     if (status){
    //         CampCommMember campComm = new CampCommMember(this, camp);
    //         camp.getCampCommitteeMembers().addToList(campComm);
    //     }
    //     else{
    //         System.out.println("Camp Committee cannot withdraw from camp.");
    //     }
    // }

    // public void viewAvailableCamps(){
    //     //for loop
    //         //if studentfacultyinfo == campfacultyinfo || isNTUcamp && within deadline
    //                 // if vacant
    //                     //print campName, campVacancy
    // }

    // public void viewRegisteredCamps() {
    //     // for loop over registered camps
    //     // print campName, student/campCommittee
    // }

    // public void registerCamp(Camp camp){
    //     //if checkCamp
    //         //register student under that camp
    //         //decrease vancany by 1

    //     //else
    //         //fk off

    // }

    // public void withdrawCamp(Camp camp){
    //     //if student is registered 
    //         //withdraw the student from camp
    //         //update vancancy 
    //         //blacklist student from camp 

    //     //else 
    //         //fk off
    // }

    // public void submitEnquiry(){
    //     //print camp list student is attending
    //     Camp camp;//ask student which camp he wants to submit enquiry to
    //     camp.getEnquiries().printList();
    //     System.out.print("Entry to be edited: ");
    //     int idx = Integer.valueOf(scanner.nextLine());
    //     Enquiry enquiry = new Enquiry(this);
    //     camp.getEnquiries().addToList(enquiry);
    // }

    // public void viewEnquiries() {
    //     //for camp in camplistregistered, printUserEnquiry() for those camps
    // }

    // public void editEnquiry() {
    //     //call viewEnquiries
    //     Camp camp; //the camp to edit
    //     // ask user to select the enquiry he wants to edit
    //     // user selects, edit that enquiry
    //     System.out.print("Entry to be edited: ");
    //     int idx = Integer.valueOf(scanner.nextLine());
    //     camp.getEnquiries().editEnquiries(idx);
    // }

    // public void deleteEnquiry() {
    //     // call viewEnquiries
    //     Camp camp; // camp to edit
    //     // ask user to select the enquiry he wants to delete
    //     // user selects, delete that enquiry
    //     System.out.print("Entry to be deleted: ");
    //     int idx = Integer.valueOf(scanner.nextLine());
    //     camp.getEnquiries().deleteFromList(idx);
    // }

    // public boolean checkCamp(Camp camp){
    // }

    // public boolean checkCampVacancy(Camp camp){
    //     if(camp.getCampInfo().getCurrentParticipantSlots() != 0)
    //         return true;
    //     else 
    //         return false;  
    // }

    // public boolean checkCampDeadline(Camp camp){
        
    // }

    // public boolean checkCampFaculty(Camp camp){

    // }

    // public boolean checkCampClash(Camp camp){

    // }





    





    
}