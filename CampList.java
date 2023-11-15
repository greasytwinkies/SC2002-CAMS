public class CampList extends List{

    public CampList(String CampName){
        super(CampName);
    }

    public void addToList(Object item){
        super.addToList(item);
    }

    public void deleteFromList(Object item){
        super.deleteFromList(item);
    }

    public void printList(){
        System.out.println(this.listName + ": ");
        int i = 1;
        for (Object item : this.list) {
            Camp camp = (Camp) item;
            System.out.println(i + ") " + camp.getCampInfo().getCampName());
            i++;
        }
        System.out.println("-End of List-\n");
    }

    public Object getFromList(int index){
        return super.getFromList(index);
    }
}