/**
    Controls the interaction between Student, Enquiry and Camp classes.
*/

public class EnquiryMenuController {

    /**
    * The student interacting with the Enquiry menu.
    */
    private Student student;

    /**
    * Creates a EnquiryMenuController class with the given Student object.
    * @param student The student interacting with the Enquiry menu.
    */
    public EnquiryMenuController(Student student){
        this.student = student;
    }

    /**
    * Allows an enquiry to be created for a given camp.
    * @param camp The camp which the enquiry is being made to.
    */
    public void submitEnquiry(Camp camp){
        Enquiry enquiry = new Enquiry(student);
        camp.getEnquiries().addToList(enquiry);
    }

    /**
    * Allows a student to view all their enquiries made for a given camp.
    * @param camp The specified camp which the student has made enquiries (if any) to.
    */
    public void viewEnquiries(Camp camp) {
        EnquiryList myEnquiry = camp.getEnquiries().returnUserEnquiry(student);
        if (myEnquiry.list.size()==0){
            System.out.println("There are no enquiries to be viewed");
            return;
        }
        camp.getEnquiries().printUserEnquiry(student);
    }

    /**
    * Allows an enquiry to be edited for a given camp.
    * @param camp The camp which the enquiry being edited belongs to.
    */
    public void editEnquiry(Camp camp) {
        EnquiryList myEnquiry = camp.getEnquiries().returnUserEnquiry(student);
        if (myEnquiry.list.size()==0){
            System.out.println("There are no enquiries to be edited");
            return;
        }
        camp.getEnquiries().printUserEnquiry(student);
        System.out.print("Entry to be edited: ");
        int idx = Integer.valueOf(Main.scan.nextLine()) - 1;
        if(idx < 0 || idx >= camp.getEnquiries().list.size()){
            System.out.println("Invalid entry number");
            return;
        }
        Enquiry enquiry = (Enquiry) camp.getEnquiries().getFromList(idx);

        if (enquiry.getReply().equals("")){
            camp.getEnquiries().editEnquiries(idx);
        }
        else{
            System.out.println("You cannot edit your entry");
        }
    }

    /**
    * Allows an enquiry to be deleted for a given camp.
    * @param camp The camp which the enquiry being deleted belongs to.
    */
    public void deleteEnquiry(Camp camp) {
        EnquiryList myEnquiry = camp.getEnquiries().returnUserEnquiry(student);
        if (myEnquiry.list.size()==0){
            System.out.println("There are no enquiries to be deleted");
            return;
        }
        camp.getEnquiries().printUserEnquiry(student);
        System.out.print("Entry to be deleted: ");
        int idx = Integer.valueOf(Main.scan.nextLine())-1;
        if(idx < 0 || idx >= camp.getEnquiries().list.size()){
            System.out.println("Invalid entry number");
            return;
        }
        Enquiry enquiry = (Enquiry) camp.getEnquiries().getFromList(idx);
        if (enquiry.getReply().equals("")){ 
            camp.getEnquiries().deleteFromList(idx);
        }
        else{
            System.out.println("You cannot delete your entry");
        }
    }
}
