import java.util.ArrayList;

public class StudentList extends List {

    public StudentList(String listName) {
        super(listName);
    }

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

    public void addToList(Student student) {
        super.addToList(student);
    }

    public void deleteFromList(Student student) {
        super.addToList(student);
    }

    public ArrayList<Object> returnStudentList() {
        return super.list;
    }

    public Object getFromList(int index){
        return super.getFromList(index);
    }
}