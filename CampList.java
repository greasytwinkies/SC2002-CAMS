import java.util.ArrayList;

public class CampList extends List{
    CampNameComparator comparator = new CampNameComparator();

    public CampList(String CampName){
        super(CampName);
    }

    public void addToList(Object item){
        super.addToList(item, comparator);
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

    /**
    * Prints the camps that correspond to the given user.
    * If the user is a Student, print the list of camps the student is registered for.
    * If the user is a Staff, print the list of camps created by the staff.
    * @param user The given user which the printed camps correspond to.
    */
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

    /**
    * Return the camps that correspond to the given user.
    * If the user is a Student, return the list of camps the student is registered for.
    * If the user is a Staff, return the list of camps created by the staff.
    * @param user The given user which the returned list of camps correspond to.
    * @return The list of camps corresponding to the given user.
    */
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


    /**
    * Return the title of Camp Committee Member associated with the given Student.
    * @param student The given student which the title of Camp Committee Member corresponds to.
    * @return The camp committee member object contained in the given student. Returns null if the student is not a camp committee member.
    */
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


    /**
    * Searches for a camp within the given campList that matches the given name.
    * @param campList The list of camps in which the given camp (name) is to be searched within.
    * @param campName The name of the camp to be searched for.
    * @return return the corresponding Camp object if there is a match. Returns null if there is no match.
    */
    public Camp findCamp(CampList campList, String campName){
        for(int i=0; i < campList.list.size() ; i++){
            if(((Camp) campList.getFromList(i)).getCampInfo().getCampName().equals(campName)) {
                return (Camp) campList.getFromList(i);
            }
        }
        return null;
    }

}