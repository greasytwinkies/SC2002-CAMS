import java.util.Scanner;

/**
 * Class to handle Suggestions by Camp Committee members, it implements the iService interface.
 */
public class Suggestion implements iService {
    /*
     * scanner to receive inputs
     */
    Scanner scanner = Main.getScanner();

    /**
     * suggestion given by Camp Committee Member
     */
    private String suggestion;

    /**
     * states of the Suggestion
     */
    public enum states {PENDING, APPROVED, REJECTED};

    /**
     * Status of the Suggestion
     */
    private states status;

    /**
     * The author of the Suggestion
     */
    protected CampCommMember author;


    /**
     * Creates a Suggestion object
     * @param author Author of the Suggestion
     */
    Suggestion(CampCommMember author) {
        create();
        this.author = author;
    }

    /**
     * Method to obtain the suggestion of the camp comm member
     * @return The suggestion of the Camp Committee member
     */
    public String getSuggestion(){return this.suggestion;}

    /**
     * Method to view suggestion created, and also its state
     */
    @Override
    public void view() {
        System.out.println("Suggestion:" + "\t" + suggestion);
        System.out.println("   Status:" + "\t" + status);
        System.out.println("");
    }

    /**
     * Method to edit a suggestion 
     */
    @Override
    public void edit() {
        // check if empty suggestion
        if(author.getCamp().getSuggestions().list.size() == 0){
            System.out.println("You have not made any suggestions");
        }
        else{
            System.out.println("Edit suggestion:");
            this.suggestion = scanner.nextLine();
            System.out.println("Suggestion edited.");
        }
        
    }

    /**
     * Method to create a suggestion, state changes to pending until further notice.
     */
    @Override
    public void create() {
        // check if empty suggestion
        System.out.println("Make a suggestion:");
        this.suggestion = scanner.nextLine();
        System.out.println("Suggestion made.");
        this.status = states.PENDING;
    }

    /**
     * Method to approve or reject a suggestion.
     */
    public void approve() {
        view();
        if (status == states.PENDING) {
            System.out.println("Select your choice:");
            System.out.println("(1) Approve Suggestion:");
            System.out.println("(2) Reject Suggestion:");
            System.out.println("(3) Exit:");
            System.out.println("");
            int choice = Integer.valueOf(scanner.nextLine());
            do {
                switch (choice) {
                    case 1:
                        System.out.println("Suggestion approved.");
                        this.status = states.APPROVED;
                        author.addPoints(1);
                        break;
                    case 2:
                        System.out.println("Suggestion rejected.");
                        this.status = states.REJECTED;
                        break;
                    case 3:
                        break;
                }
            } while (choice < 1 || choice > 3);
        }
    }
    
}