import java.util.ArrayList;
/**
    Represents the list of all camps.
*/
public class CampList extends List{
    CampNameComparator comparator = new CampNameComparator();

    /**
     * Creates a list.
     * @param ListName The name of the list.
     */
    public CampList(String ListName){
        super(ListName);
    }

    /**
     * Adds the item to the list.
     * @param item The item to be added to the list.
     */
    public void addToList(Object item){
        super.addToList(item, comparator);
    }

    /**
     * Prints the list of camps.
     */
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

    public void printUserCamp(User user){
        if (user instanceof Staff){ System.out.println("Camps created by " + user.getName() + ":");}
        else {System.out.println("Camps Registered as Camp Attendee:");}
        int i =1;
        int flag =0;
        for (Object item : super.list){
            Camp camp = (Camp) item;

            if(user instanceof Staff){
                if(camp.getCampInfo().getStaffInCharge().getUserID().equals(user.getUserID())){
                    System.out.println(i + ") " + camp.getCampInfo().getCampName());
                    i++;
                    flag=1;                
                }
            }
            else if (user instanceof Student){
                ArrayList<Object> attendees = camp.getCampAttendeesList().returnStudentList();
                for (Object thing : attendees){
                    Student student = (Student) thing;
                    if (student.getUserID().equals(user.getUserID())){
                        System.out.println(i + ") " + camp.getCampInfo().getCampName());
                        i++;
                        flag=1;
                        break;
                    }
                }
            }
        }
            if (flag==0 && user instanceof Staff){  System.out.println("You have not created camps");}
            else if (flag==0 && user instanceof Student){    System.out.println("You have not registered for any camps");}
            else{System.out.println("-End of list-");}
        }

    public CampList returnUserCamps(User user){
        CampList userCamps = new CampList(user.getName() + "'s camps");
        int flag =0;
        for (Object item : super.list){
            Camp camp = (Camp) item;

            if(user instanceof Staff){
                if(camp.getCampInfo().getStaffInCharge().getUserID().equals(user.getUserID())){
                    userCamps.addToList(camp);
                    flag =1;
                }
            }
            else if (user instanceof Student){
                ArrayList<Object> attendees = camp.getCampAttendeesList().returnStudentList();
                for (Object thing : attendees){
                    Student student = (Student) thing;
                    if (student.getUserID().equals(user.getUserID())){
                        userCamps.addToList(camp);
                        flag=1;
                        break;
                    }
                }
            }
        }
            if (flag==0){ return null; }
            return userCamps;
    }
        
    public CampCommMember findCampCommMember(Student student){
        for (Object item : super.list){
            Camp camp = (Camp)item;
            ArrayList<Object> memberlist = camp.getCampCommitteeMembersList().returnStudentList();
            for (Object member : memberlist){
                Student person = (Student) member;
                if (person.getUserID().equals(student.getUserID())){
                    return person.getCampComm();
                }
            }
        }
        return null;
    }

    public Camp findCamp(CampList campList, String campName){
        for(int i=0; i < campList.list.size() ; i++){
            if(((Camp) campList.getFromList(i)).getCampInfo().getCampName().equals(campName)) {
                return (Camp) campList.getFromList(i);
            }
        }
        return null;
    }

}