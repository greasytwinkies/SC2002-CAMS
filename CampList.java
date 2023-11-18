import java.util.ArrayList;

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

    // public void printUserCamp(Staff staff){
    //     System.out.println(super.listName);
    //     int i =1;
    //     for (Object item : super.list){
    //         Camp camp = (Camp) item;

    //         if(camp.getCampInfo().getStaffInCharge().equals(staff)){
    //             System.out.println(i + ") " + camp.getCampInfo().getCampName());
    //         }
    //         // if(user instanceof Staff){
    //         //     if(camp.getCampInfo().getStaffInCharge().getUserID().equals(user.getUserID())){
    //         //         System.out.println(i + ") " + camp.getCampInfo().getCampName());
    //         //     }
    //         // }
    //         // else if (user instanceof Student){
    //         //     if(camp.getCampAttendeesList().returnStudentList().contains(user)){
    //         //         System.out.println(i + ") " + camp.getCampInfo().getCampName());
    //         //     }
    //         // }
    //         i++;
    //     }
    //     System.out.println("-End of list-");
    // }

    public void printUserCamp(User user){
        System.out.println(super.listName);
        int i =1;
        int flag =0;
        for (Object item : super.list){
            Camp camp = (Camp) item;

            if(user instanceof Staff){
                if(camp.getCampInfo().getStaffInCharge().getUserID().equals(user.getUserID())){
                    System.out.println(i + ") " + camp.getCampInfo().getCampName() + " (" + camp.getCampInfo().getCurrentParticipantSlots() + " vacancies)");
                    flag=1;
                }
            }
            else if (user instanceof Student){
                ArrayList<Object> attendees = camp.getCampAttendeesList().returnStudentList();
                for (Object thing : attendees){
                    Student student = (Student) thing;
                    if (student.getUserID().equals(user.getUserID())){
                        System.out.println(i + ") " + camp.getCampInfo().getCampName() + " (" + camp.getCampInfo().getCurrentParticipantSlots() + " vacancies)");
                        flag=1;
                        break;
                    }
                }
            }
            i++;
        }
            if (flag==0 && user instanceof Staff){  System.out.println("You have not created camps");}
            else if (flag==0 && user instanceof Student){    System.out.println("You have not registered for any camps");}
            else{System.out.println("-End of list-");}
        }
        


    public Object getFromList(int index){
        return super.getFromList(index);
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