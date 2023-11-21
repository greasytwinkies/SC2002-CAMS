import java.util.ArrayList;
import java.io.FileNotFoundException;

public class CampCommitteeReport implements Report {

    @Override
    public void printReport(Camp camp) {
        //student name\t
        StudentList campMembersList = camp.getCampMembersList();
        if (campMembersList.list.size() == 0) { 
            System.out.println(">>> NO CAMP COMMITTEE MEMBERS <<< \n");
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