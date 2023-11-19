public class SuggestionList extends List {
    public SuggestionList(String listName){
        super(listName);
    }

    public void printList(){
        System.out.println(super.listName + ":");
        int i=1;
        for (Object item : super.list){
            Suggestion s = (Suggestion) item;
            System.out.print( i + ") ");
            s.view();
            i++;
        }
        System.out.println("-End of List-");
    } 

    public void printUserSuggestions(CampCommMember author){
        System.out.println("Viewing your suggestions: ");
        int i=1;
        for (Object item : super.list){
            Suggestion s = (Suggestion) item;
            if (s.author==author){
                System.out.print( i + ") ");
                s.view();
            }
            i++;
        }
        System.out.println("-End of List-");
    }

    public void editSuggestions(int idx){
        Suggestion newSuggestion = (Suggestion) super.list.get(idx-1);
        newSuggestion.edit();
    }

    public void deleteFromList(int idx){
        super.list.remove(idx-1);
    }

    public void approveSuggestions(){
        printList();
        System.out.print("Entry to approve/reject: ");
        int idx = Integer.valueOf(scanner.nextLine());
        Suggestion sug = (Suggestion) super.list.get(idx-1);
        sug.approve();
    }
    
}
