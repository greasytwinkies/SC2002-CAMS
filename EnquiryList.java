import java.util.Comparator;
import java.util.Scanner;

public class EnquiryList extends List{
    Scanner scanner = Main.getScanner();
    EnquiryComparator comparator = new EnquiryComparator();


    public EnquiryList(String listName){
        super("List of enquiries");
    }

    public void addToList(Enquiry e){
        super.addToList(e, comparator);
    }

    public void printList(){
        System.out.println(super.listName + ": ");
        int i=1;
        for (Object item : super.list){
            Enquiry e = (Enquiry) item;
            System.out.print( i + ") ");
            e.view();
            i++;
        }
        System.out.println("-End of List-");
    }  

    public void printUserEnquiry(Student author){
        System.out.println(super.listName + " by " + author.getUserID() + ": " );
        int i=1;
        for (Object item : super.list){
            Enquiry e = (Enquiry) item;
            if (e.author.getUserID().equals(author.getUserID())){
                System.out.print( i + ") ");
                e.view();
            }
            i++;
        }
        System.out.println("-End of List-");
    }

    public void editEnquiries(int idx){
        Enquiry newEnquiry = (Enquiry) super.list.get(idx);
        newEnquiry.edit();
        updateList(comparator);
        System.out.println("Succesfully edited!");
    }

    public void deleteFromList(int idx){
        super.list.remove(idx);
        System.out.println("Successfully deleted!");
    }

    public void replyEnquiries(){
        printList();
        System.out.print("Entry to reply to: ");
        int idx = Integer.valueOf(scanner.nextLine())-1;
        Enquiry enquiry = (Enquiry) super.list.get(idx);
        enquiry.reply();
        System.out.println("Successfully replied");
    }

}