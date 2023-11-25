/**
    Represents a list of Suggestions.
*/
public class SuggestionList extends List {
    /**
    * The comparator object used to compare two Suggestion objects.
    */
    SuggestionComparator comparator = new SuggestionComparator();

    /**
    * Creates a List of Suggestions with the given name.
    * @param listName The name for the list of Suggestions.
    */
    public SuggestionList(String listName){
        super(listName);
    }

    /**
    * Adds the given suggestion to the list.
    * @param s The suggestion object to be added to the list.
    */
    public void addToList(Suggestion s){
        super.addToList(s, comparator);
    }

    /**
    * Prints the list of suggestions.
    */
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

    /**
    * Prints a list of suggestions created by the given author
    * @param author The specified author for the list of suggestions to be filtered by.
    */
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

    /**
    * Allows for a specified suggestion within the list to be edited.
    * @param idx The index of the specified suggestion (within the given list) to be edited.
    */
    public void editSuggestions(int idx){
        Suggestion newSuggestion = (Suggestion) super.list.get(idx);
        newSuggestion.edit();
        updateList(comparator);
    }

    /**
    * Allows for a specified suggestion within the list to be deleted.
    * @param idx The index of the specified suggestion (within the given list) to be deleted.
    */
    public void deleteFromList(int idx){
        super.list.remove(idx);
    }

    /**
    * Allows for a specified suggestion within the list to be approved by Staff.
    */
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
