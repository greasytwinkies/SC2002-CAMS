import java.time.LocalDate;

public class Camp {
    private CampInformation campInfo;

    // private EnquiryList enquiries;
    // private SuggestionList suggestions;
    private StudentList CampAttendeesList;
    private StudentList CampCommitteeMembersList;
    private StudentList CampMemberList;

    public Camp(CampInformation campInformation, Staff staffInCharge){
        this.campInfo = campInformation;
        campInformation.setStaffInCharge(staffInCharge);

    }

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