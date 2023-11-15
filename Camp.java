import java.time.LocalDate;

public class Camp {
    private CampInformation campInfo;
    // private EnquiryList enquiries;
    // private SuggestionList suggestions;
    private StudentList CampAttendeesList;
    private StudentList CampCommitteeMembersList;
    private StudentList CampMemberList;

    public Camp(String staffName){
        campInfo = new CampInformation(staffName);

    }

    // public Camp(String campName, LocalDate startingDate, LocalDate endingDate, LocalDate registrationClosingDate,
    //         boolean userGroup, String location, int totalParticipantSlots, int totalCampCommitteeSlots,
    //         String description, Staff staffName) {
    //     campInfo = new CampInformation();

    //     campInfo.setCampName(campName);
    //     campInfo.setStartingDate(startingDate);
    //     campInfo.setEndingDate(endingDate);
    //     campInfo.setRegistrationClosingDate(registrationClosingDate);
    //     // campInfo.setUserGroup(userGroup); // What is the purpose of this UserGroup?
    //     campInfo.setLocation(location);
    //     campInfo.setTotalParticipantSlots(totalParticipantSlots);
    //     campInfo.setTotalCampCommitteeSlots(totalCampCommitteeSlots);
    //     campInfo.setDescription(description);
    //     campInfo.setStaffInCharge(staffName);

    //     // Initialize the current slots to zero
    //     campInfo.setCurrentParticipantSlots(0);
    //     campInfo.setCurrentCampCommitteeSlots(0);
    //     campInfo.setCampVisibility(true); // By default, the camp is visible

    //     // Initialize the lists
    //     StudentList CampAttendeesList = new StudentList();
    //     CampCommitteeMembersList = new StudentList();
    //     CampMemberList = new StudentList();
    // }

    // public void registerStudent(Student studentName) {
    //     if (campInfo.getCurrentParticipantSlots() < campInfo.getTotalParticipantSlots()) {
    //         // campInfo.getParticipantMembers().add(studentName);
    //         CampMemberList.addToList(studentName);
    //         CampAttendeesList.addToList(studentName); // what is the difference between CampMembers and CampAttendees?
    //         campInfo.setCurrentParticipantSlots(campInfo.getCurrentParticipantSlots() + 1);
    //     } else {
    //         System.out.println("No more slots available for participants.");
    //     }
    // }

    // public void registerCommitteeMember(CampCommMember committeeMemberName) {
    //     if (campInfo.getCurrentCampCommitteeSlots() < campInfo.getTotalCampCommitteeSlots()) {
    //         // campInfo.getCampCommitteeMembers().add(committeeMemberName);
    //         // campInfo.getCampMembers().add(committeeMemberName);
    //         campInfo.setCurrentCampCommitteeSlots(campInfo.getCurrentCampCommitteeSlots() + 1);
    //     } else {
    //         System.out.println("No more slots available for committee members.");
    //     }
    // }
    public CampInformation getCampInfo(){  return this.campInfo;    }
    // public EnquiryList getEnquiries(){ return this.enquiries;   }
    // public SuggestionList getSuggestions(){ return this.suggestions;    }

    // public StudentList getCampAttendeesList() {return CampAttendeesList;}

    // public StudentList getCampCommitteeMembersList(){return CampCommitteeMembersList;} 

    // public StudentList getCampMembersList(){return this.CampMemberList;}

    // public void printCampInfo(){}


}