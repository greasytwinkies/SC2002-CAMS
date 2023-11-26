import java.util.InputMismatchException;
import java.util.Scanner;

/**
    Represents the login page for users to log into CAMS.
*/
public class LoginPage {
    /**
    * Reflects the logout status of the user.
    * By default, the user is logged in (not logged out).
    */
    private static int logout = 0;

    /**
    * Allows user to login to CAMS.
    */
    public static User login() {
        int choice = 0;
        setLogout(0);

        // create a list of student based on student database
        StudentList studentList = new StudentList("Studentlist");
        StudentTextDB.populateStudentList(studentList);
        // studentList.printList();

        // create a list of staff based on staff database
        StaffList staffList = new StaffList("stafflist");
        StaffTextDB.populateStaffList(staffList);
        // staffList.printList();

        Scanner scan = Main.getScanner();

        do {

            System.out.println("Welcome to NTU's CAMS service. "
                    + "\nPlease select login option that applies to you:"
                    + "\n\n1) Student."
                    + "\n2) Staff."
                    + "\n3) Create new Student/Staff account."
                    + "\n\nEnter any other number to quit.");

            boolean validInput = false;
            while (!validInput) {
                try {
                    choice = scan.nextInt();
                    validInput = true;
                    scan.nextLine();
                } catch (InputMismatchException e ) {
                    System.out.println("Please enter a number.");
                    scan.nextLine();
                }
            }

            switch (choice) {
                case 1:
                    System.out.println("Please enter your student ID");
                    System.out.println("");
                    String studentID = scan.nextLine();

                    System.out.println("Please enter your student password");
                    System.out.println("");
                    String StudentPassword = scan.nextLine();

                    System.out.println("USERID: " + studentID + " password: " + StudentPassword);
                    System.out.println("");
                    return studentLogin(studentList, studentID, StudentPassword);

                case 2:
                    System.out.println("Please enter your staff ID:");
                    System.out.println("");
                    String staffID = scan.nextLine();

                    System.out.println("Please enter your staff password:");
                    System.out.println("");
                    String StaffPassword = scan.nextLine();

                    System.out.println("USERID: " + staffID + " password: " + StaffPassword);
                    System.out.println("");
                    return staffLogin(staffList, staffID, StaffPassword);

                case 3:
                    System.out.println("Create new account:");
                    System.out.println("1) Student");
                    System.out.println("2) Staff");
                    choice = Integer.valueOf(scan.nextLine());
                    
                    if (choice == 1) {
                        Student createdStudent = createStudentAccount();
                        StudentTextDB.createStudent(createdStudent);
                        return createdStudent;
                    }
                    if (choice == 2) {
                        System.out.println("Please verify staff access using unique password: ");
                        String adminPassword = scan.next();
                        if(adminPassword.equals("admin")){
                            Staff createdStaff = createStaffAccount();
                            StaffTextDB.createStaff(createdStaff);
                            return createdStaff;
                        }
                        else{
                            System.out.println("Cannot access this feature if not an admin. ");
                            return null;
                        }
                        

                    }

            }

        } while (choice < 4);

        System.out.println("Thank you for using NTU's CAM service."
                + "\nWe Hope to see you again soon.");

        return null;
    }

    /**
    * Extracts userID from a given email address.
    * ie. "KOH1" in "KOH1@e.ntu.edu.sg".
    * @param Email The given email for the userID to be extracted from.
    * @return The userID extracted from the given email address.
    */
    public static String ExtractUserName(String Email) {
        int EndIndex = Email.indexOf("@");
        if (EndIndex != -1) {
            return (Email.substring(0, EndIndex));
        } else {
            return "Invalid Email.";
        }
    }


    /**
    * Allows a new student account to be created.
    * @return Returns the new Student object being created.
    */
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


    /**
    * Allows a new staff account to be created.
    * @return Returns the new Staff object being created.
    */
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

    /**
    * Facilitates the login of a Student based on the given list of existing student accounts, as well as the inputted userID and password.
    * @param studentList List of existing (registered) student accounts.
    * @param UserID The input student ID.
    * @param StudentPassword The input student password.
    * @return Returns the respective student object corresponding to the input details. If the userID and/or password is invalid, a null object is returned.
    */
    public static Student studentLogin(StudentList studentList, String UserID, String StudentPassword) {
        Scanner scan = Main.getScanner();
        int sizeStudent = StudentTextDB.getSize();
        for (int i = 0; i < sizeStudent; i++) {
            Student student = (Student) studentList.getFromList(i);

            try{
                if (UserID.toLowerCase().equals(ExtractUserName(student.getUserID()).toLowerCase())
                    && StudentPassword.equals(student.getPassword())) {
                System.out.println(">>> LOGIN SUCCESSFUL <<<\n");
                if (student.getPassword().equals("password")) {
                    System.out.println("Your password has not been changed.");
                    System.out.println("Please change your password.");
                    changePassword(student);
                    // force student to log out
                    return null;
                }
                return student;
            }
            }
            catch(NullPointerException e){
                break;
            }
            
            
        }
        System.out.println("Log in failed\n");
        System.out.println("Invalid user or wrong password");
        return null;

    }


    /**
    * Facilitates the login of a Staff based on the given list of existing staff accounts, as well as the inputted userID and password.
    * @param staffList List of existing (registered) staff accounts.
    * @param UserID The input staff ID.
    * @param StaffPassword The input staff password.
    * @return Returns the respective staff object corresponding to the input details. If the userID and/or password is invalid, a null object is returned.
    */
    public static Staff staffLogin(StaffList staffList, String UserID, String StaffPassword) {
        Scanner scan = Main.getScanner();
        int sizeStaff = StaffTextDB.getSize();
        for (int i = 0; i < sizeStaff; i++) {
            Staff staff = (Staff) staffList.getFromList(i);

            // try{
            //     System.out.println(student.getUserID());
            // }
            // catch (NullPointerException e){
            //     continue;
            // }

            try{
                if (UserID.toLowerCase().equals(ExtractUserName(staff.getUserID()).toLowerCase())
                    && StaffPassword.equals(staff.getPassword())) {
                System.out.println(">>> LOGIN SUCCESSFUL <<<\n");
                if (staff.getPassword().equals("password")) {
                    System.out.println("Your password has not been changed.");
                    System.out.println("Please change your password.");
                    changePassword(staff);
                    // force staff to log out
                    return null;
                }
                return staff;
            }
            }
            catch(NullPointerException e){
                break;
            }
            
            
        }
        System.out.println("Log in failed\n");
        System.out.println("Invalid user or wrong password");
        return null;
    }

    /**
    * Allows the given user's password to be changed.
    * @param user The user whose password is to be changed.
    */
    public static void changePassword(User user) {
        Scanner scan = Main.getScanner();
        String prevPassInput = "";
        String newPass = "";
        String prevPass = user.getPassword();
        boolean oldPassMatch = false;
        boolean checkNewPass = false;
        do {
            System.out.println("Please enter old password:");
            prevPassInput = scan.nextLine();
            System.out.println("");
            if (prevPassInput.equals(prevPass)) {
                oldPassMatch = true;
                // prompt for new password
                // block user from entering previous password again
                do {
                    System.out.println("Please enter new password:");
                    newPass = scan.nextLine();
                    System.out.println("");
                    if (newPass.equals("password")) {
                        System.out.println("Cannot change password to default password!");
                        System.out.println("Please try again.");
                    }
                    else if (newPass.equals(prevPass)) {
                        System.out.println("Cannot enter same password as old password.");
                        System.out.println("Please try again.");
                    }
                    else {
                        checkNewPass = true;
                        user.changePassword(newPass);
                        if (user instanceof Student ) {StudentTextDB.updatePassword((Student)user);};
                        if (user instanceof Staff ) {StaffTextDB.updatePassword((Staff)user);};
                        System.out.println("Successfully updated password!");
                        System.out.println("Please log back in to verify the changes.");
                    }
                } while (checkNewPass == false);
            }
            else { System.out.println("Incorrect password entered. Please try again!"); }
        } while (oldPassMatch == false);
    }

    /**
    * Returns the current logout status.
    */
    public static int getLogout() {
        return logout;
    }

    /**
    * Sets the current logout status with the given input.
    * @param i Reflects the logout status after the function has been executed. 1 represents logged out, while 0 represents logged in.
    */
    public static void setLogout(int i) {
        logout = i;
    }

    /**
    * Executes a logout.
    * @return The logout status after the method has been executed.
    */
    public static int Logout() {
        setLogout(1);
        return getLogout();
    }

}