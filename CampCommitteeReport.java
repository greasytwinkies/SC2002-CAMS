import java.util.ArrayList;

public class CampCommitteeReport implements Report {

    @Override
    public void printReport(Camp camp) {
        //student name\t
        StudentList campMembersList = camp.getCampMembersList();
        if (campMembersList.list.size() == 0) { 
            System.out.println("There are no camp committee members in this camp!\n");
            return;
        }
        int count = 0;
        System.out.println("Name\tPoints");
        for (int i=0; i<=campMembersList.list.size(); i++) {
            Student student = (Student) campMembersList.list.get(i);
            if (student.getCampComm().getCamp().equals(camp)) {
                count++;
                System.out.println(count + ") " + student.getName() + "\t" + student.getCampComm().getPoints());
            }

        }

    }

}