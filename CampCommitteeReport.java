public class CampCommitteeReport implements Report {

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