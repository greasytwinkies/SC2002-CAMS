import java.util.Scanner;

public class StudentReport implements Report {

    Scanner scanner = Main.getScanner();

    @Override
    public void printReport(Camp camp) {
        int c = reportFilter();
        if (c == 1) {
            printAllStudents();
            iterateOverList(camp.getCampMembersList(), camp);
        }
        if (c == 2) {
            printParticipants();
            iterateOverList(camp.getCampAttendeesList(), camp);
        }
        if (c == 3) {
            printCampComm();
            iterateOverList(camp.getCampCommitteeMembersList(), camp);
        }
    }

    public int reportFilter() {
        System.out.println("Filter by 1) All students 2) Participants only 3) Camp Committee only");
        int choice = Integer.valueOf(scanner.nextLine());
        return choice;
    }

    public void printAllStudents() {
        System.out.println("Generating report for all students:\n");
    }
    public void printParticipants() {
        System.out.println("Generating report for all participants:\n");
    }
    public void printCampComm() {
        System.out.println("Generating report for all camp committee members:\n");
    }

    public void iterateOverList(StudentList studentList, Camp camp) {
        for (int i=1; i<=studentList.list.size(); i++ ) {
            Student student = (Student) studentList.list.get(i-1);
            
            System.out.print(i + ") " + student.getName() + "\t");
            if (student.getCampComm().getCamp().equals(camp)) {
                System.out.println("COMMITTEE MEMBER");
            }
            else {
                System.out.println("PARTICIPANT");
            }
        }
    }

}