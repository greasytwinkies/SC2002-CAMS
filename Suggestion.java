import java.util.Scanner;

public class Suggestion implements iService {
    Scanner scanner = Main.getScanner();
    private String suggestion;
    public enum states {PENDING, APPROVED, REJECTED};
    private states status;
    protected CampCommMember author;

    Suggestion(CampCommMember author) {
        create();
        this.author = author;
    }

    @Override
    public void view() {
        System.out.println("Suggestion:" + "\t" + suggestion);
        System.out.println("   Status:" + "\t" + status);
        System.out.println("");
    }

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

    @Override
    public void create() {
        // check if empty suggestion
        System.out.println("Make a suggestion:");
        this.suggestion = scanner.nextLine();
        System.out.println("Suggestion made.");
        this.status = states.PENDING;
    }

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