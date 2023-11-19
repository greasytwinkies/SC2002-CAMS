import java.util.Scanner;

public class CampCommMember{
    private Camp camp;
    private static int points;
    Scanner scanner = Main.getScanner();

    public CampCommMember(Camp camp){
        this.camp = camp;
        this.points = 0;
    }

    public void addPoints(int num){ this.points += num;}

    public int getPoints(){
        return this.points;
    }

    public Camp getCamp(){
        return this.camp;
    }

    public void submitSuggestions(Suggestion suggestion){
        camp.getSuggestions().addToList(suggestion);
        points+=1;
    }

    public void viewMySuggestions(){
        camp.getSuggestions().printUserSuggestions(this);
    }

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