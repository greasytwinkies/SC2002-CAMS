public class Camp {
    private CampInformation campInfo;

    private EnquiryList enquiries;
    private SuggestionList suggestions;
    // private SuggestionList suggestions;
    private StudentList CampAttendeesList;
    private StudentList CampCommitteeMembersList;
    private StudentList CampMemberList;
    private StudentList WithdrawnStudentList;

    public Camp(CampInformation campInformation, Staff staffInCharge){
        this.campInfo = campInformation;
        this.campInfo.setStaffInCharge(staffInCharge);
        CampAttendeesList = new StudentList("Camp Attendees");
        CampCommitteeMembersList = new StudentList("Camp Committee Members");
        enquiries = new EnquiryList("Enquiry List");
        suggestions = new SuggestionList("Suggestion List");
        WithdrawnStudentList = new StudentList("Withdrawn students");
        CampMemberList = new StudentList("All Members");
    }

    public CampInformation getCampInfo(){  return this.campInfo;    }
    public EnquiryList getEnquiries(){ return enquiries;}
    public SuggestionList getSuggestions(){ return this.suggestions;    }

    public StudentList getCampAttendeesList() {return CampAttendeesList;}

    public StudentList getCampCommitteeMembersList(){return CampCommitteeMembersList;} 

    public StudentList getCampMembersList(){return this.CampMemberList;}

    public StudentList getWithdrawnStudentList(){return this.WithdrawnStudentList;}

    public void printCampInfo(){
        System.out.println(getCampInfo().getCampName());
    }

}