import java.time.*;
import java.time.chrono.ChronoLocalDateTime;

public class CampInformation {
	private boolean CampVisibility;
	private String CampName;

	private LocalDate StartingDate;
	private LocalDate EndingDate;
	private LocalDate RegistrationClosingDate;

	private Faculty faculty; 
	private String Location;

	private int TotalParticipantSlots;
	private int CurrentParticipantSlots;

	private int TotalCampCommitteeSlots;
	private int CurrentCampCommitteeSlots;

	private int TotalCampMemberSlots; /* = TotalParticipantSlots + TotalCampCommitteeSlots; */
	private int CurrentCampMemberSlots; /* = CurrentParticipantSlots + CurrentCampCommitteeSlots; */

	private String Description;
	private Staff StaffInCharge;

	public CampInformation(){ 
	}

    public CampInformation(String CampName){
        this.CampName = CampName;

    }

	public void printCampInformation(){

	}

	public boolean getCampVisibility() {
		return CampVisibility;
	}

	public void setCampVisibility(boolean isVisible) {
		CampVisibility = isVisible;
	}

	public String getCampName() {
		return CampName;
	}

	public void setCampName(String campName) {
		CampName = campName;
	}

	public LocalDate getStartingDate() {
		return StartingDate;
	}

	public void setStartingDate(LocalDate startingDate) {
		StartingDate = startingDate;
	}

	public LocalDate getEndingDate() {
		return EndingDate;
	}

	public void setEndingDate(LocalDate endingDate2) {
		EndingDate = endingDate2;
	}

	public LocalDate getRegistrationClosingDate() {
		return RegistrationClosingDate;
	}

	public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
		RegistrationClosingDate = registrationClosingDate;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty choice) {
		faculty = choice;
		if(choice == Faculty.NTU){
			System.out.println("Set as NTU-wide");
		}else{
			System.out.println("Set as Faculty-specific");
		}
		
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Staff getStaffInCharge() {
		return StaffInCharge;
	}

	public void setStaffInCharge(Staff staffInCharge) {
		StaffInCharge = staffInCharge;
	}

	public int getCurrentParticipantSlots() {
		return CurrentParticipantSlots;
	}

	public void setCurrentParticipantSlots(int currentParticipantSlots) {
		CurrentParticipantSlots = currentParticipantSlots;
	}

	public int getCurrentCampCommitteeSlots() {
		return CurrentCampCommitteeSlots;
	}

	public void setCurrentCampCommitteeSlots(int currentCampCommitteeSlots) {
		CurrentCampCommitteeSlots = currentCampCommitteeSlots;
	}

	public int getTotalParticipantSlots() {
		return TotalParticipantSlots;
	}

	public void setTotalParticipantSlots(int totalParticipantSlots) {
		TotalParticipantSlots = totalParticipantSlots;
	}

	public int getTotalCampCommitteeSlots() {
		return TotalCampCommitteeSlots;
	}

	public void setTotalCampCommitteeSlots(int totalCampCommitteeSlots) {
		TotalCampCommitteeSlots = totalCampCommitteeSlots;
	}

	public int getCurrentCampMemberSlots() {
		return CurrentCampMemberSlots;
	}

	public void setCurrentCampMemberSlots(int currentCampMemberSlots) {
		CurrentCampMemberSlots = currentCampMemberSlots;
	}

	public String toString() {
		return "Camp Name: " + this.CampName + "\n"
		+  "Visibility: " + (String.valueOf(this.CampVisibility)).toUpperCase() + "\n"
		+  "Starting Date: " + this.StartingDate.toString() + "\n"
		+  "Ending Date: " + this.EndingDate.toString() + "\n"
		+  "Registration Closing Date: " + this.RegistrationClosingDate.toString() + "\n"
		+  "Faculty: " + this.faculty.toString() + "\n"
		+  "Location: " + this.Location + "\n"
		+  "Camp Participant Slots (Filled/Empty/Total): " + this.CurrentParticipantSlots + "/" + (this.TotalParticipantSlots - this.CurrentParticipantSlots) + "/" + this.TotalParticipantSlots + "\n"
		+  "Camp Committee Member Slots (Filled/Empty/Total): " + this.CurrentCampCommitteeSlots + "/" + (this.TotalCampCommitteeSlots - this.CurrentCampCommitteeSlots) + "/" + this.TotalCampCommitteeSlots + "\n"
		+  "Description: " + this.Description + "\n"
		+  "Staff-In-Charge: " + this.getStaffInCharge().getName() + "\n"
		+ "\n";
	}

}