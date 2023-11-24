public class SuggestionList extends List {
    SuggestionComparator comparator = new SuggestionComparator();

    public SuggestionList(String listName){
        super(listName);
    }

    public void addToList(Suggestion s){
        super.addToList(s, comparator);
    }

    public void printList(){
        if (this.list.size()>0){
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
        else{   System.out.println("There are no suggestions!");}
    
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
        Suggestion newSuggestion = (Suggestion) super.list.get(idx);
        newSuggestion.edit();
        updateList(comparator);
        // ArrayList<Suggestion> s = returnList();
    }

    public void deleteFromList(int idx){
        super.list.remove(idx);
    }

    public void approveSuggestions(){
        printList();
        if (this.list.size()>0){
            System.out.print("Entry to approve/reject: ");
            int idx = Integer.valueOf(scanner.nextLine())-1;
            Suggestion sug = (Suggestion) super.list.get(idx);
            sug.approve();
        }
    }

}
