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

    public boolean updateList(Comparator comparator){
        Collections.sort(list, comparator);
        return true;
    }

    public boolean addToList(Object item, Comparator comparator) {
        if (this.list.contains(item)) {
            return false;
        } else {
            this.list.add(item);
            Collections.sort(list, comparator);
            return true;
        }
    };

    public boolean deleteFromList(Object item) {
        if (this.list.contains(item)) {
            this.list.remove(item);
            return true;
        } else {
            return false;
        }
    };

    public Object getFromList(int index) {
        if (index >= 0 && index < this.list.size()) {
            return this.list.get(index);
        } else {
            return null; // or throw an exception
        }
    }
    
}