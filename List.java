import java.util.*;

public abstract class List {

    protected String listName;
    protected ArrayList<Object> list;
    Scanner scanner = new Scanner(System.in);

    public List(String listName) {     
        this.listName = listName;
        this.list = new ArrayList<Object>();
    }

    public abstract void printList();

    public void updateList(Comparator comparator){
        Collections.sort(list, comparator);
    }

    public void addToList(Object item, Comparator comparator) {
        if (this.list.contains(item)) {
            System.out.println("Item already exists in list");
        } else {
            this.list.add(item);
            Collections.sort(list, comparator);
            //System.out.println("Successfully added item");
        }
    };

    public void deleteFromList(Object item) {
        if (this.list.contains(item)) {
            this.list.remove(item);
            System.out.println("Successfully removed a item!");
        } else {
            System.out.println("Error: item does not exist in list");
        }
    };

    public Object getFromList(int index) {
        if (index >= 0 && index < this.list.size()) {
            return this.list.get(index);
        } else {
            System.out.println("Error: Index out of bounds");
            return null; // or throw an exception
        }
    }
    
}