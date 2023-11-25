import java.util.ArrayList;

/**
 * Class for creating a list of Student objects, inherits from List class
 */
public class StudentList extends List {

    /**
     * Compares two names and checks if they're identical
     */
    NameComparator comparator = new NameComparator();


    /**
     * Creates a StudentList Object 
     * @param listName The name of the Student List
     */
    public StudentList(String listName) {
        super(listName);
    }

    /**
     * Method to print the List 
     */
    public void printList(){
        System.out.println(this.listName + ": ");
        int i = 1;
        for (Object item : this.list) {
            Student student = (Student) item;
            System.out.println(i + ") " + student.getName());
            i++;
        }
        System.out.println("-End of List-\n");
    }

    /**
     * Methof to add a Student object to the Student List
     * @param student Student to be added to the List
     */
    public void addToList(Student student) {
        super.addToList(student, comparator);
    }

    /**
     * Method to return a StudentList when called
     * @return The Student List
     */
    public ArrayList<Object> returnStudentList() {
        return super.list;
    }


    /**
     * Return a specific object from the list at a speecific index
     * @param index The index within the list of the individual
     * @return Individual from that specific list
     */
    public Object getFromList(int index){
        return super.getFromList(index);
    }
}