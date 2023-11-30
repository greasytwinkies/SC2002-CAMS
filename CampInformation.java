import java.time.LocalDate;
/**
	Represents the information of a camp.
 */
public class CampInformation {
	/**
	 * The visibility of the camp.
	 */
	private boolean CampVisibility;
	/**
	 * The name of the camp.
	 */
	private String CampName;

	/**
	 * The starting date of the camp.
	 */
	private LocalDate StartingDate;
	/**
	 * The ending date of the camp.
	 */
	private LocalDate EndingDate;
	/**
	 * The registration closing date of the camp.
	 */
	private LocalDate RegistrationClosingDate;

	/**
	 * The faculty the camp belongs to.
	 */
	private Faculty faculty;
	/**
	 * The location of the camp.
	 */
	private String Location;

	/**
	 * The total number of camp participants.
	 */
	private int TotalParticipantSlots;
	/**
	 * The current number of available camp participant slots left.
	 */
	private int CurrentParticipantSlots;

	/**
	 * The total number of camp committee.
	 */
	private int TotalCampCommitteeSlots;
	/**
	 * The current number of available camp committee slots left.
	 */
	private int CurrentCampCommitteeSlots;

	/**
	 * The total number of camp participant and camp committee slots left.
	 */
	private int TotalCampMemberSlots;
	/**
	 * The current number of camp participant and camp committee slots left.
	 */
	private int CurrentCampMemberSlots;

	/**
	 * The camp description.
	 */
	private String Description;
	/**
	 * The staff in charge of the camp.
	 */
	private Staff StaffInCharge;

	/**
	 * Creates a new camp information object to be filled with details later.
	 */
	public CampInformation(){
	}

	/**
	 * Sets the name of the camp.
	 * @param CampName The name of the camp.
	 */
    public CampInformation(String CampName){
        this.CampName = CampName;

    }

	/**
	 * Gets the camp's visibility.
	 * @return Boolean value if the camp is visible.
	 */
	public boolean getCampVisibility() {
		return CampVisibility;
	}

	/**
	 * Sets the camp visibility to be visible/ hidden.
	 * @param isVisible The boolean value if it is viisble.
	 */
	public void setCampVisibility(boolean isVisible) {
		CampVisibility = isVisible;
	}

	/**
	 * Gets the name of the camp.
	 * @return The camp's name.
	 */
	public String getCampName() {
		return CampName;
	}

	/**
	 * Sets the name of the camp.
	 * @param campName This is the name of the camp.
	 */
	public void setCampName(String campName) {
		CampName = campName;
	}

	/**
	 * Gets the starting date of the camp.
	 * @return The starting date of the camp.
	 */
	public LocalDate getStartingDate() {
		return StartingDate;
	}

	/**
	 * Sets the starting date of the camp.
	 * @param startingDate This is the starting date of the camp.
	 */
	public void setStartingDate(LocalDate startingDate) {
		StartingDate = startingDate;
	}

	/**
	 * Gets the ending date of the camp.
	 * @return The ending date of the camp.
	 */
	public LocalDate getEndingDate() {
		return EndingDate;
	}

	/**
	 * Sets the ending date of the camp.
	 * @param endingDate2 This is the ending date of the camp.
	 */
	public void setEndingDate(LocalDate endingDate2) {
		EndingDate = endingDate2;
	}

	/**
	 * Gets the registration closing date of the camp.
	 * @return The registration closing date of the camp.
	 */
	public LocalDate getRegistrationClosingDate() {
		return RegistrationClosingDate;
	}

	/**
	 * Sets the registration closing date of the camp.
	 * @param registrationClosingDate This is the registration closing date of the camp.
	 */
	public void setRegistrationClosingDate(LocalDate registrationClosingDate) {
		RegistrationClosingDate = registrationClosingDate;
	}

	/**
	 * Gets the faculty of the camp.
	 * @return The camp's faculty.
	 */
	public Faculty getFaculty() {
		return faculty;
	}

	/**
	 * Sets the faculty of the camp.
	 * @param choice The choice to set the cmap as NTU wide or as faculty specific
	 */
	public void setFaculty(Faculty choice) {
		faculty = choice;
		if(choice == Faculty.NTU){
			System.out.println("Set as NTU-wide");
		}else{
			System.out.println("Set as Faculty-specific");
		}
		
	}

	/**
	 * Gets the location of the camp.
	 * @return The location of the camp.
	 */
	public String getLocation() {
		return Location;
	}

	/**
	 * Sets the location of the camp.
	 * @param location The location of the camp.
	 */
	public void setLocation(String location) {
		Location = location;
	}

	/**
	 * Gets the camp's description.
	 * @return The camp's description.
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Sets the camp's description.
	 * @param description The camp's description.
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * Gets the staff in charge.
	 * @return The staff in charge.
	 */
	public Staff getStaffInCharge() {
		return StaffInCharge;
	}

	/**
	 * Sets the staff in charge.
	 * @param staffInCharge The staff in charge.
	 */
	public void setStaffInCharge(Staff staffInCharge) {
		StaffInCharge = staffInCharge;
	}

	/**
	 * Gets the number of current participant slots available.
	 * @return The number of current participant slots available.
	 */
	public int getCurrentParticipantSlots() {
		return CurrentParticipantSlots;
	}

	/**
	 * Sets the number of current participant slots available.
	 * @param currentParticipantSlots The number of current participant slots available.
	 */
	public void setCurrentParticipantSlots(int currentParticipantSlots) {
		CurrentParticipantSlots = currentParticipantSlots;
	}

	/**
	 * Gets the number of current camp committee slots available.
	 * @return the number of current camp committee slots available.
	 */
	public int getCurrentCampCommitteeSlots() {
		return CurrentCampCommitteeSlots;
	}

	/**
	 * Sets the number of current camp committee slots available.
	 * @param currentCampCommitteeSlots The number of current camp committee slots available.
	 */
	public void setCurrentCampCommitteeSlots(int currentCampCommitteeSlots) {
		CurrentCampCommitteeSlots = currentCampCommitteeSlots;
	}

	/**
	 * Gets the total number of camp committee and participant slots available.
	 * @return The the total number of camp committee and participant slots available.
	 */
	public int getTotalParticipantSlots() {
		return TotalParticipantSlots;
	}

	/**
	 * Sets the total number of camp committee and participant slots available.
	 * @param totalParticipantSlots The total number of camp committee and participant slots available.
	 */
	public void setTotalParticipantSlots(int totalParticipantSlots) {
		TotalParticipantSlots = totalParticipantSlots;
	}

	/**
	 * Gets the total number of camp committee slots of the camp.
	 * @return The total number of camp committee slots of the camp.
	 */
	public int getTotalCampCommitteeSlots() {
		return TotalCampCommitteeSlots;
	}

	/**
	 * Sets the the total number of camp committee slots of the camp.
	 * @param totalCampCommitteeSlots The total number of camp committee slots of the camp.
	 */
	public void setTotalCampCommitteeSlots(int totalCampCommitteeSlots) {
		TotalCampCommitteeSlots = totalCampCommitteeSlots;
	}

	/**
	 * Gets the number of camp committee and camp participant slots available.
	 * @return The number of camp committee and camp participant slots available.
	 */
	public int getCurrentCampMemberSlots() {
		return CurrentCampMemberSlots;
	}

	/**
	 * Sets number of camp committee and camp participant slots available.
	 * @param currentCampMemberSlots The number of number of camp committee and camp participant slots available.
	 */
	public void setCurrentCampMemberSlots(int currentCampMemberSlots) {
		CurrentCampMemberSlots = currentCampMemberSlots;
	}

	/**
	 * Prints out all the camp's information.
	 */
	public String toString() {
		return "Camp Name: " + this.CampName + "\n"
		+  "Visibility: " + (String.valueOf(this.CampVisibility)).toUpperCase() + "\n"
		+  "Starting Date: " + this.StartingDate.toString() + "\n"
		+  "Ending Date: " + this.EndingDate.toString() + "\n"
		+  "Registration Closing Date: " + this.RegistrationClosingDate.toString() + "\n"
		+  "Faculty: " + this.faculty.toString() + "\n"
		+  "Location: " + this.Location + "\n"
		+  "Camp Participant Slots (Empty/Filled/Total): " + this.CurrentParticipantSlots + "/" + (this.TotalParticipantSlots - this.CurrentParticipantSlots) + "/" + this.TotalParticipantSlots + "\n"
		+  "Camp Committee Member Slots (Empty/Filled/Total): " + this.CurrentCampCommitteeSlots + "/" + (this.TotalCampCommitteeSlots - this.CurrentCampCommitteeSlots) + "/" + this.TotalCampCommitteeSlots + "\n"
		+  "Description: " + this.Description + "\n"
		+  "Staff-In-Charge: " + this.getStaffInCharge().getName() + "\n"
		+ "\n";
	}

}