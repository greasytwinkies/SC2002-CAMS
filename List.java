import java.util.*;
/**
    Represents a collection of objects.
*/
public abstract class List {

    /**
    * The name of the given list.
    */
    protected String listName;

    /**
    * The actual list being made.
    */
    protected ArrayList<Object> list;

    /**
    * Scanner for inputs.
    */
    Scanner scanner = new Scanner(System.in);

    /**
    * Creates a List with the given name.
    * @param listName The name of the given list.
    */
    public List(String listName) {     
        this.listName = listName;
        this.list = new ArrayList<Object>();
    }

    /**
    * Abstract implementation of a method to print all items in the list.
    */
    public abstract void printList();

    /**
    * Updates the ordering of the given list by a given comparator.
    * @param comparator The specified comparator for the list to be ordered by.
    * @return Reflects that the list has been sorted.
    */
    public boolean updateList(Comparator comparator){
        Collections.sort(list, comparator);
        return true;
    }

    /**
    * Adds the given item to the list, and then sorts the new list by the given comparator.
    * @param item The item being added to the list.
    * @param comparator The specified comparator for the updated list to be sorted by.
    * @return Reflects whether the object has been successfully added to the list; Item will not be added if the same item is already in the list.
    */
    public boolean addToList(Object item, Comparator comparator) {
        if (this.list.contains(item)) {
            return false;
        } else {
            this.list.add(item);
            Collections.sort(list, comparator);
            return true;
        }
    };

    /**
    * Deletes the given item from the list.
    * @param item The item being added to the list.
    * @return Reflects whether the object has been successfully deleted from the list; Item cannot be deleted if it does not originally exist in the list.
    */
    public boolean deleteFromList(Object item) {
        if (this.list.contains(item)) {
            this.list.remove(item);
            return true;
        } else {
            return false;
        }
    };

    /**
    * Returns the object that corresponds to the given index in the list.
    * @param idx The index of the object in the list to be returned.
    * @return Returns the object corresponding to the given index in the list. Returns null if the index specified is out of bounds.
    */
    public Object getFromList(int index) {
        if (index >= 0 && index < this.list.size()) {
            return this.list.get(index);
        } else {
            return null; // or throw an exception
        }
    }
    
}