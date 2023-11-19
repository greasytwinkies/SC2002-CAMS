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


    // public void generateReport(){}; //prob not void bah
    
}