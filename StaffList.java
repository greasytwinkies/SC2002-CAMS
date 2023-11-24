public class StaffList extends List
{
    NameComparator comparator = new NameComparator();

    public StaffList(String listName) {
        super(listName);
    }

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

    public void addToList(Staff staff) {
        super.addToList(staff, comparator);
    }
}