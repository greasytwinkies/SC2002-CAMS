import java.util.Scanner;

public class Enquiry implements iService {
    Scanner scanner = new Scanner(System.in);
    private String enquiry;
    private String reply = "";
    protected Student author;

    Enquiry(Student author) {
        create();
        this.author = author;
    }

    public String getEnquiry(){return this.enquiry;}
    public String getReply(){return this.reply;}
    public Student getAuthor(){return this.author;}
   
    @Override
    public void view(){
        System.out.println("Enquiry: " + enquiry);
        if (reply.equals("")){
            System.out.println("Reply: PENDING");
        } else {    System.out.println("Reply: " + reply);}
    }

    @Override
    public void edit() {
        if (reply.equals("")) {
            System.out.println("Enter your new enquiry:");
            this.enquiry = scanner.nextLine();
            System.out.println("Enquiry edited.");
        }

    }

    @Override
    public void create() {
        System.out.println("Enter your enquiry:");
        this.enquiry = scanner.nextLine();
        System.out.println("Enquiry made.");
    }

    public void reply() {
        if (reply.equals("")) {
            System.out.println("Enter your reply:");
            this.reply = scanner.nextLine();
            System.out.println("Reply made.");
        }
    }
}