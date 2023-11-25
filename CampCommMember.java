import java.util.Scanner;
/**
    Represents the title of camp committee by a student.
*/
public class CampCommMember{
    /**
     * The camp the camp committee member belongs to.
     */
    private Camp camp;
    /**
     * The number of points a camp committee member has.
     */
    private static int points;
    /**
     * Scanner for inputs.
     */
    Scanner scanner = Main.getScanner();

    /**
     * Creates a new camp committee title given to a student. The Student who becomes a camp committee starts with 0 points.
     * @param camp The camp the camp committee member belongs to.
     */
    public CampCommMember(Camp camp){
        this.camp = camp;
        this.points = 0;
    }

    /**
     * Adds a point to the camp committee member.
     * @param num
     */
    public void addPoints(int num){ this.points += num;}

    /**
     * Gets the number of points the camp committee member has.
     * @return This is the number of points the camp committee member has.
     */
    public int getPoints(){
        return this.points;
    }

    /**
     * Gets the camp of camp committee.
     * @return This is the camp the camp committee member belongs to.
     */
    public Camp getCamp(){
        return this.camp;
    }

    /**
     * Submits the suggestion made by a camp committee member and gets awarded 1 point.
     * @param suggestion This is the suggestion made by camp committee.
     */
    public void submitSuggestions(Suggestion suggestion){
        camp.getSuggestions().addToList(suggestion);
        points+=1;
    }

    /**
     * prints the suggestions made by the camp committee memeber.
     */
    public void viewMySuggestions(){
        camp.getSuggestions().printUserSuggestions(this);
    }
    
}