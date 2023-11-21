import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginPage {
    private static int logout = 0;

    public static User login() {
        int choice = 0;
        setLogout(0);

        // create a list of student based on student database
        StudentList studentList = new StudentList("Studentlist");
        StudentTextDB.populateStudentList(studentList);
        studentList.printList(); // this function doesnt work the way its supposed to...

        // create a list of staff based on staff database
        StaffList staffList = new StaffList("stafflist");
        StaffTextDB.populateStaffList(staffList);
        staffList.printList(); //// this function doesnt work the way its supposed to...

        Scanner scan = Main.getScanner();

        do {

            System.out.println("Welcome to NTU's CAMS service. "
                    + "\nPlease select login option that applies to you:"
                    + "\n\n1) Student."
                    + "\n2) Staff."
                    + "\n3) Create new Student/Staff account."
                    + "\n4) Quit."
                    + "\n");

            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = scan.nextInt();
                    validInput = true;
                    scan.nextLine();
                } catch (InputMismatchException e ) {
                    System.out.println("Please enter a number instead.");
                    scan.nextLine();
                }
            }

            switch (choice) {
                case (1):
                    System.out.println("Please enter your student email");
                    System.out.println("");
                    String StudentEmail = scan.nextLine();

                    System.out.println("Please enter your student password");
                    System.out.println("");
                    String StudentPassword = scan.nextLine();

                    String UserID = ExtractUserName(StudentEmail);

                    System.out.println("USERID: " + UserID + " password: " + StudentPassword);
                    System.out.println("");
                    return studentLogin(studentList, UserID, StudentPassword);

                case (2):
                    System.out.println("Please enter your staff email:");
                    System.out.println("");
                    String StaffEmail = scan.nextLine();

                    System.out.println("Please enter your staff password:");
                    System.out.println("");
                    String StaffPassword = scan.nextLine();

                    UserID = ExtractUserName(StaffEmail);

                    System.out.println("USERID: " + UserID + " password: " + StaffPassword);
                    System.out.println("");
                    return staffLogin(staffList, UserID, StaffPassword);

                case (3):
                    System.out.println("Create new account:");
                    System.out.println("1) Student");
                    System.out.println("2) Staff");
                    choice = scan.nextInt();
                    if (choice == 1) {
                        Student createdStudent = createStudentAccount();
                        StudentTextDB.createStudent(createdStudent);
                        return createdStudent;
                    } else if (choice == 2) {
                        System.out.println("Please verify staff access using unique password: ");
                        // TODO: make some sort of check before allowing creating of a staff account
                        Staff createdStaff = createStaffAccount();
                        StaffTextDB.createStaff(createdStaff);
                        return createdStaff;

                    }

            }

        } while (choice < 4);

        System.out.println("Thank you for using NTU's CAM service."
                + "\nWe Hope to see you again soon.");

        scan.close();
        User user = new User("nil", "nil", "nil", Faculty.NTU);

        return null;
    }

    public static String ExtractUserName(String Email) {
        int EndIndex = Email.indexOf("@");
        if (EndIndex != -1) {
            return (Email.substring(0, EndIndex));
        } else {
            return ("Invalid Email.");
        }
    }

    public static Student createStudentAccount() {
        Scanner scan = Main.getScanner();
        scan.nextLine();
        System.out.println("Please enter your name");
        String name = scan.nextLine().toUpperCase();
        System.out.println("Please enter your faculty");
        System.out.println("1) ADM");
        System.out.println("2) EEE");
        System.out.println("3) SCSE");
        System.out.println("4) NBS");
        System.out.println("5) others");
        int choice = scan.nextInt();
        Faculty faculty;
        switch (choice) {
            case (1):
                faculty = Faculty.ADM;
                break;
            case (2):
                faculty = Faculty.EEE;
                break;
            case (3):
                faculty = Faculty.SCSE;
                break;
            case (4):
                faculty = Faculty.NBS;
                break;
            default:
                faculty = Faculty.NTU;
                break;
        }
        scan.nextLine();
        System.out.println("Please enter your student email");
        String StudentEmail = scan.nextLine();
        System.out.println("Please enter your password");
        String StudentPassword = scan.nextLine();
        String UserID = ExtractUserName(StudentEmail).toUpperCase();
        Student createdStudent = new Student(name, UserID + "@e.ntu.edu.sg", StudentPassword, faculty);
        return createdStudent;
    }

    public static Staff createStaffAccount() {
        Scanner scan = Main.getScanner();
        scan.nextLine();
        System.out.println("Please enter your name");
        String name = scan.nextLine();
        System.out.println("Please enter your faculty");
        System.out.println("1) ADM");
        System.out.println("2) EEE");
        System.out.println("3) SCSE");
        System.out.println("4) NBS");
        System.out.println("5) others");
        int choice = scan.nextInt();
        Faculty faculty;
        switch (choice) {
            case (1):
                faculty = Faculty.ADM;
                break;
            case (2):
                faculty = Faculty.EEE;
                break;
            case (3):
                faculty = Faculty.SCSE;
                break;
            case (4):
                faculty = Faculty.NBS;
                break;
            default:
                faculty = Faculty.NTU;
                break;
        }
        scan.nextLine();
        System.out.println("Please enter your staff email");
        String StaffEmail = scan.nextLine();
        System.out.println("Please enter your password");
        String StaffPassword = scan.nextLine();
        String UserID = ExtractUserName(StaffEmail).toUpperCase();
        Staff createdStaff = new Staff(name, UserID + "@E.NTU.EDU.SG", StaffPassword, faculty);
        return createdStaff;
    }

    public static Student studentLogin(StudentList studentList, String UserID, String StudentPassword) {
        Scanner scan = Main.getScanner();
        int sizeStudent = StudentTextDB.getSize();
        for (int i = 0; i < sizeStudent; i++) {
            Student student = (Student) studentList.getFromList(i);

            System.out.println(student.getUserID());

            if (UserID.equals(ExtractUserName(student.getUserID()))
                    && StudentPassword.equals(student.getPassword())) {
                System.out.println("Username and Password accepted!");
                if (student.getPassword().equals("password")) {

                    System.out.println("Your password has not been changed"
                            + "\nWould you like to change your password? (Y/N)");

                    if (scan.nextLine().toUpperCase().equals("Y")) {
                        changeStudentPassword(student);
                    }
                }
                return student;
            }
        }
        System.out.println("Invalid user or wrong password");
        System.out.println("Log in failed");
        return null;

    }

    public static Staff staffLogin(StaffList staffList, String UserID, String StaffPassword) {
        Scanner scan = Main.getScanner();
        int sizeStaff = StaffTextDB.getSize();
        for (int i = 0; i < sizeStaff; i++) {
            Staff staff = (Staff) staffList.getFromList(i);

            System.out.println(staff.getUserID());

            if (UserID.equals(ExtractUserName(staff.getUserID()))
                    && StaffPassword.equals(staff.getPassword())) {
                System.out.println("Username and Password accepted!");
                if (staff.getPassword().equals("Password")) {

                    System.out.println("Your password has not been changed"
                            + "\nWould you like to change your password? (Y/N)");

                    if (scan.nextLine().toUpperCase().equals("Y")) {
                        changeStaffPassword(staff);
                    }
                }
                return staff;
            }
        }
        System.out.println("Invalid user or wrong password");
        System.out.println("Log in failed");
        return null;

    }

    public static void changeStudentPassword(Student student) {
        Scanner scan = Main.getScanner();
        System.out.println("Please enter old password");
        String oldStudentPassword = scan.nextLine();
        if (oldStudentPassword.equals(student.getPassword())) {
            System.out.println("Please enter new password");
            String StudentPassword = scan.nextLine();
            student.changePassword(StudentPassword);
            StudentTextDB.updatePassword(student);
            System.out.println("Successfully updated password!");
        } else {
            System.out.println("Wrong password entered. Unable to update password.");
        }

    }

    public static void changeStaffPassword(Staff staff) {
        Scanner scan = Main.getScanner();
        System.out.println("Please enter old password");
        String oldStaffpassword = scan.nextLine();
        if (oldStaffpassword.equals(staff.getPassword())) {
            System.out.println("Please enter new password");
            String Staffpassword = scan.nextLine();
            staff.changePassword(Staffpassword);
            StaffTextDB.updatePassword(staff);
            System.out.println("Successfully updated password!");
        } else {
            System.out.println("Wrong password entered. Unable to update password.");
        }

    }

    public static int getLogout() {
        return logout;
    }

    public static void setLogout(int i) {
        logout = i;
    }

    public static int Logout() {
        setLogout(1);
        return getLogout();
    }

}