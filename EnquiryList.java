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
        if (this.list.size()>0){
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
    else{   System.out.println("There are no enquiries!");}
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
        Enquiry e = (Enquiry) super.list.remove(idx);
        if (e!=null){ System.out.println("Successfully deleted!");}
        else System.out.println("Cannot delete!");
    }

    public void replyEnquiries(){
        printList();
        if (this.list.size()>0){
            System.out.print("Entry to reply to: ");
            int idx = Integer.valueOf(scanner.nextLine())-1;
            Enquiry enquiry = (Enquiry) super.list.get(idx);
            enquiry.reply();
            System.out.println("Successfully replied");
        }
    }

}