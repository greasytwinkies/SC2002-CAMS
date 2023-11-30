

import java.util.Scanner;
/**
    Represents an enquiry made by a camp participant (prospective or current).
*/
public class Enquiry implements iService {
    /**
    * Scanner
    */
    Scanner scanner = Main.getScanner();

    /**
    * The actual enquiry being made.
    */
    private String enquiry;

    /**
    * The reply to an enquiry by either a Camp Committee Member or Staff.
    */
    private String reply = "";

    /**
    * The creator of the enquiry.
    */
    protected Student author;

    /**
    * Creates a Enquiry with the given Student object.
    * @param author The student who created the enquiry.
    */
    Enquiry(Student author) {
        create();
        this.author = author;
    }

    /**
    * Gets this enquiry.
    * @return This Enquiry.
    */
    public String getEnquiry(){return this.enquiry;}

    /**
    * Gets the reply to this enquiry.
    * @return The reply to this enquiry.
    */
    public String getReply(){return this.reply;}

    /**
    * Gets the creator of this enquiry.
    * @return The creator of this enquiry.
    */
    public Student getAuthor(){return this.author;}
   
    /**
    * Prints the given enquiry, as well as whether it has been replied to or not.
    */
    @Override
    public void view(){
        System.out.println("Enquiry: " + enquiry);
        if (reply.equals("")){
            System.out.println("Reply: PENDING");
        } else {    System.out.println("Reply: " + reply);}
    }

    /**
    * Allows the creator to edit the given enquiry.
    */
    @Override
    public void edit() {
        if (reply.equals("")) {
            System.out.println("Enter your new enquiry:");
            this.enquiry = scanner.nextLine();
            System.out.println("Enquiry edited.");
        }

    }

    /**
    * Allows the given enquiry to be created.
    */
    @Override
    public void create() {
        System.out.println("Enter your enquiry:");
        this.enquiry = scanner.nextLine();
        System.out.println("Enquiry made.");
    }

    /**
    * Allows a reply to be made to the given enquiry.
    */
    public void reply() {
        if (reply.equals("")) {
            System.out.println("Enter your reply:");
            this.reply = scanner.nextLine();
            System.out.println("Reply made.");
        }
    }
}