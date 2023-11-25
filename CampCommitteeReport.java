/**
    Represents the report of camp committee members.
*/
public class CampCommitteeReport implements Report {

    /**
     * Prints the report of the camp committee members in the camp, indicating how many points the camp committee member has.
     * @param camp This is the camp for which the camp commitee report would be printed based on.
     */
    @Override
    public void printReport(Camp camp) {
        //student name\t
        StudentList campCommList = camp.getCampCommitteeMembersList();
        if (campCommList.list.size() == 0) { 
            System.out.println(">>> NO CAMP COMMITTEE MEMBERS <<< \n");
            return;
        }
        int count = 0;
        System.out.println("Name\tPoints");
        for (int i=0; i<campCommList.list.size(); i++) {
            Student student = (Student) campCommList.list.get(i);
            count++;
            System.out.println(count + ") " + student.getName() + "\t" + student.getCampComm().getPoints());
        }
    }

}