import java.util.Scanner;

public class CampCommMember extends User{
    private Camp camp;
    private int points;
    Scanner scan = Main.getScanner();

    public CampCommMember(String name, String userID, String password, Faculty facultyInformation, Camp camp){
        super(name, userID, password, facultyInformation);
        this.camp = camp;
        this.points = 0;
    }

    public void addPoints(int num){ this.points += num;}

    // public void submitSuggestions(){
    //     Suggestion suggestion = new Suggestion(this);
    //     camp.getSuggestions().addToList(suggestion);
    //     points+=1; 
    // }

    // public void viewMySuggestions(){
    //     camp.getSuggestions().printUserSuggestions(this);
    // }

    // public void editMySuggestions(){
    //     viewMySuggestions();
    //     System.out.print("Entry to be edited: ");
    //     int idx = Integer.valueOf(scanner.nextLine());
    // }

    // public void deleteMySuggestions(){
    //     viewMySuggestions();
    //     System.out.print("Entry to be deleted: ");
    //     int idx = Integer.valueOf(scanner.nextLine());
    //     camp.getSuggestions().deleteFromList(idx);
    // }

    // public void viewEnquiries(){
    //     camp.getEnquiries().printList();
    // }

    // public void replyEnquiries(){
    //     camp.getEnquiries().replyEnquiries();
    //     points+=1;
    // }

    // public void viewCampDetails(){};
    // public void generateReport(){}; //prob not void bah
    
}