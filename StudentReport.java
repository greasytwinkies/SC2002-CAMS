import java.io.*;
import java.util.Scanner;

/**
 * Class to create a Student Report of a specific camp, implements the Report interface
 */
public class StudentReport implements Report {

    /**
     * Scanner object too receive inputs
     */
    Scanner scanner = Main.getScanner();


    /**
     * Method to print a Camps report
     * @param camp Camp of the report to be printed
     */
    @Override
    public void printReport(Camp camp) throws FileNotFoundException {
        StudentList filterList = filter(camp);
        PrintStream o = new PrintStream(new File("studentReport.txt"));
        PrintStream console = System.out;
        System.setOut(o);
        printList(camp, filterList);
        System.setOut(console);
    }

    /**
     * Method to print the Report with specific filter
     * @param camp Name of the camp for the report
     * @param filterList The list of Students to be printed
     */
    public void printReportFiltered (Camp camp, StudentList filterList) {
        printList(camp, filterList);
    }

    /**
     * Method to print list of Students, indicating if they are Participants or Committee members.
     * @param camp Camp to generate a List for
     * @param filterList Filtered list of students being used
     */
    public void printList(Camp camp, StudentList filterList) {
        System.out.println(camp.getCampInfo().getCampName());
        if (filterList.list.size() == 0) {
            System.out.println(">>> NO MATCHING RESULTS FOR SPECIFIED FILTER <<<\n");
            return;
        }
        System.out.println("Name\tStatus");
        for (int i=1; i<=filterList.list.size(); i++ ) {
            Student student = (Student) filterList.list.get(i-1);
            
            System.out.print(i + ") " + student.getName() + "\t");
            CampCommMember campComm = student.getCampComm();
            if (campComm == null || !campComm.getCamp().equals(camp)) {
                System.out.println("PARTICIPANT");
            }
            else if (student.getCampComm().getCamp().equals(camp)) {
                System.out.println("COMMITTEE MEMBER");
            }

        }
        System.out.println("");
    }

    /**
     * Method to filter a camps participants by all of its members
     * @param camp Camp to be filtered
     * @return List of all Camp Members, including both particiants and camp committee members
     */
    public StudentList filterAllAttendees(Camp camp) { return camp.getCampMembersList(); }

    /**
     * Method to filter a camps members by only its participants, not its camp comm members
     * @param camp Camp to be filtered
     * @return List of all participant members
     */
    public StudentList filterAllParticipants(Camp camp) { return camp.getCampAttendeesList(); }

     /**
     * Method to filter a camps members by only its camp comm members, not its participants
     * @param camp Camp to be filtered
     * @return List of all camp comm members
     */
    public StudentList filterAllCampComm(Camp camp) { return camp.getCampCommitteeMembersList(); }

    /**
     * Method to find a specific student in a camp
     * @param camp Camp to be filtered
     * @param name Name of the Student to be located
     * @return a StudentList of the student
     */
    public StudentList filterByName (Camp camp, String name )
    {             
            StudentList student = new StudentList("specific student");
            // search if student in camp, add to list
            for (int i=0; i<camp.getCampMembersList().list.size(); i++) {
                Student searchStudent = (Student) camp.getCampMembersList().list.get(i);
                if (searchStudent.getName().equals(name)) {
                    student.addToList(searchStudent);
                }
            }
            return student;
    }

    /**
     * Method to print a reports for all camps in a camp list
     * @param campList List of camps to generate reports of
     * @throws FileNotFoundException When camp list does not exist
     */
    public void printReportsForAllCamps (CampList campList) throws FileNotFoundException {
        System.out.println("Filter all camps by: 1) All camp attendees 2) Camp participants only 3) Camp Committee members only 4) A specific individual\n");
        int filter = Integer.valueOf(scanner.nextLine());
        String name = "";
        if (filter == 4) {
            System.out.println("Enter name of specific student:");
            name = scanner.nextLine();
        }

        PrintStream o = new PrintStream(new File("studentReport.txt"));
        PrintStream console = System.out;
        System.setOut(o);
        for (int x=0; x<campList.list.size(); x++) {
            Camp camp = (Camp) campList.list.get(x);
            switch (filter) {
                case 1: // all camp attendees
                    printReportFiltered(camp, filterAllAttendees(camp));
                    break;
                case 2: 
                    printReportFiltered(camp, filterAllParticipants(camp));
                    break;
                case 3: 
                    printReportFiltered(camp, filterAllCampComm(camp));
                    break;
                case 4: 
                    printReportFiltered(camp, filterByName(camp, name));
                    break;
            }
        }
        System.setOut(console);
    }

    /**
     * Method to filter a specific camp by certain parameters
     * @param camp Camp to be filtered
     * @return The filtered camp (By Camp comm, Participants, all)
     */
    public StudentList filter(Camp camp) {
        System.out.println("Filter by: 1) All camp attendees 2) Camp participants only 3) Camp Committee members only 4) A specific individual\n");
        int filter = Integer.valueOf(scanner.nextLine());
        StudentList filteredList = new StudentList("filtered list");
        if (filter == 1) { filteredList = camp.getCampMembersList(); } // all camp attendees
        else if (filter == 2) { filteredList = camp.getCampAttendeesList(); } // participants only
        else if (filter == 3) { filteredList = camp.getCampCommitteeMembersList(); } // camp comm only
        else if (filter == 4) { // specific individual
            System.out.println("Enter name of specific student:");
            String studentName = scanner.nextLine();
            // search if student in camp, add to list
            for (int i=0; i<camp.getCampMembersList().list.size(); i++) {
                Student searchStudent = (Student) camp.getCampMembersList().list.get(i);
                if (searchStudent.getName().equals(studentName)) {
                    filteredList.addToList(searchStudent);
                }
            }
        }
        return filteredList;
    }
}