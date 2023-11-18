public class EnquiryMenuController {

    private Student student;

    public EnquiryMenuController(Student student){
        this.student = student;
    }

    public void submitEnquiry(Camp camp){
        Enquiry enquiry = new Enquiry(student);
        camp.getEnquiries().addToList(enquiry);
    }

    public void viewEnquiries(Camp camp) {
        camp.getEnquiries().printUserEnquiry(student);
    }

    public void editEnquiry(Camp camp) {
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

    public void deleteEnquiry(Camp camp) {
        System.out.println("Error debugging 2");
        System.out.println("Camp debugging: " + camp);
        camp.getEnquiries().printUserEnquiry(student);
        System.out.print("Entry to be deleted: ");
        int idx = Integer.valueOf(Main.scan.nextLine());
        System.out.println("Accessing enquiries now");
        Enquiry enquiry = (Enquiry) camp.getEnquiries().getFromList(idx);
        // if (enquiry.getReply().equals("")){ // some error here
        camp.getEnquiries().deleteFromList(idx);
        // }
        // else{
        //     System.out.println("You cannot delete your entry");
        // }
    }
}
