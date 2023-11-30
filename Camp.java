/**
    Represents a camp created by staff that students can register for as participants or committee members.
*/

public class Camp {
    /**
     * The camps information.
     */
    private CampInformation campInfo;

    /**
     * The list of enquries a camp has.
     */
    private EnquiryList enquiries;

    /**
     * The list of suggestions a camp has.
     */
    private SuggestionList suggestions;
    
    /**
     * The list of students as camp attendees.
     */
    private StudentList CampAttendeesList;

    /**
     * The list of students as camp committee members.
     */
    private StudentList CampCommitteeMembersList;

    /**
     * The list of students consisting of camp attendees and camp committees.
     */
    private StudentList CampMemberList;

    /**
     * The list of students who withdrew from this camp.
     */
    private StudentList WithdrawnStudentList;

    /**
     * Creates a new camp with the given campInformation, staffInCharge.
     * Initialises the following lists: campAttendeesList, CampCommitteeMembersList, enquiries, suggestions, WithdrawnStudentList, CampMemberList.
     * @param campInformation This is the camp infroamtion.
     * @param staffInCharge This is the staff in charge of the camp.
     */
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

    /**
     * Gets the information of the camp.
     * @return
     */
    public CampInformation getCampInfo(){  return this.campInfo;}

    /**
     * Gets the enquiry list of the camp.
     * @return
     */
    public EnquiryList getEnquiries(){ return enquiries;}

    /**
     * Gets the suggestion list of the camp.
     */
    public SuggestionList getSuggestions(){ return this.suggestions;}

    /**
     * Gets the camp Attendee list of the camp.
     * @return
     */
    public StudentList getCampAttendeesList() {return CampAttendeesList;}

    /**
     * Gets the camp committee list of the camp.
     * @return
     */
    public StudentList getCampCommitteeMembersList(){return CampCommitteeMembersList;}

    /**
     * Gets the camp attendees and committees list of the camp.
     * @return
     */
    public StudentList getCampMembersList(){return this.CampMemberList;}

    /**
     * Gets the withdrawn student list of the camp.
     * @return
     */
    public StudentList getWithdrawnStudentList(){return this.WithdrawnStudentList;}

    /**
     * Prints the camps name.
     */
    public void printCampInfo(){
        System.out.println(getCampInfo().getCampName());
    }

}