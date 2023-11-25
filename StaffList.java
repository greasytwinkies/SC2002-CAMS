/**
    Represents a list of staff objects.
*/

public class StaffList extends List
{
    /**
    * Allows the StaffList to be sorted based on names.
    */
    NameComparator comparator = new NameComparator();

    /**
    * Creates a list of staff with the given name.
    * @param listName The name of the list.
    */
    public StaffList(String listName) {
        super(listName);
    }

    /**
    * Prints the list of staff.
    */
    public void printList(){
        System.out.println(this.listName + ": ");
        int i = 1;
        for (Object item : this.list) {
            Staff staff = (Staff) item;
            System.out.println(i + ") " + staff.getName());
            i++;
        }
        System.out.println("-End of List-\n");
    }

    /**
    * Prints adds a given Staff to the staff list.
    * @param staff The staff object to be added to the list.
    */
    public void addToList(Staff staff) {
        super.addToList(staff, comparator);
    }
}