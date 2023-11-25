


import java.util.Scanner;
/**
    Represents a list of Enquiry objects.
*/
public class EnquiryList extends List{
    /**
    * Scanner
    */
    Scanner scanner = Main.getScanner();

    /**
    * The comparator object used to compare two Enquiry objects.
    */
    EnquiryComparator comparator = new EnquiryComparator();

    /**
    * Creates a List of Enquiries with the given name.
    * @param listName The name for the list of Enquiries.
    */
    public EnquiryList(String listName){
        super("List of enquiries");
    }

    /**
    * Adds the given Enquiry to the list.
    * @param e The Enquiry object to be added to the list.
    */
    public void addToList(Enquiry e){
        super.addToList(e, comparator);
    }

    /**
    * Prints the list of enquiries.
    */
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

    /**
    * Prints a list of enquiries created by the given author.
    * @param author The specified author for the list of enquiries to be filtered by.
    */
    public void printUserEnquiry(Student author){
        System.out.println(super.listName + " by " + author.getName() + ": " );
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

    /**
    * Returns a list of enquiries created by the given author
    * @param author The specified author for the list of enquiries to be filtered by.
    * @return The list of Enquiries created by the given author.
    */
    public EnquiryList returnUserEnquiry(Student author){
        EnquiryList newlist = new EnquiryList("Enquiries by " + author.getName());
        for (Object item : super.list){
            Enquiry e = (Enquiry) item;
            if (e.author.getUserID().equals(author.getUserID())){
                newlist.addToList(e);;
            }
        }
        return newlist;
    }

    /**
    * Allows for a specified enquiry within the list to be edited.
    * @param idx The index of the specified enquiry (within the given list) to be edited.
    */
    public void editEnquiries(int idx){
        Enquiry newEnquiry = (Enquiry) super.list.get(idx);
        newEnquiry.edit();
        updateList(comparator);
        System.out.println("Successfully edited!");
    }

    /**
    * Allows for a specified enquiry within the list to be deleted.
    * @param idx The index of the specified enquiry (within the given list) to be deleted.
    */
    public void deleteFromList(int idx){
        Enquiry e = (Enquiry) super.list.remove(idx);
        if (e!=null){ System.out.println("Successfully deleted!");}
        else System.out.println("Cannot delete!");
    }

    /**
    * Allows for a specified enquiry within the list to be replied to (by a Camp Committee Member or Staff).
    */
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