import java.util.Scanner;
import java.lang.String;


public class LoginPage 
{
    
    public static User login()
	{
		int choice = 0;

        //create a list of student based on student database
        StudentList studentList = new StudentList("Studentlist");
        StudentTextDB.populateStudentList(studentList);
        studentList.printList(); //this function doesnt work the way its supposed to...
        
        //create a list of staff based on staff database
        StaffList staffList = new StaffList("stafflist");
        StaffTextDB.populateStaffList(staffList);
        staffList.printList(); ////this function doesnt work the way its supposed to...

        //create a list of camps
        CampList campList = new CampList("campList");

        //testing
        // Camp trialCamp = new Camp("trailCamp");
        // campList.addToList(trialCamp);
        // campList.printList();


		Scanner scan = new Scanner(System.in);
		
		do{
		
		System.out.println("Welcome to NTU's CAMS service. "
				+ "\nPlease select login option that applies to you:" 
				+ "\n\n1) Student."
				+ "\n2) Staff."
				+ "\n3) Quit.");
		
		choice = scan.nextInt();
        scan.nextLine();
				
			switch(choice) 
			{
			case(1):
                System.out.println("Please enter your student email");
                String StudentEmail = scan.nextLine();
                System.out.println("Please enter your student password");
                String StudentPassword = scan.nextLine();

                String UserID = ExtractUserName(StudentEmail);
    
                System.out.println("USERID: " + UserID + " password: " + StudentPassword);
                return studentLogin(studentList, UserID, StudentPassword);
				
			
			case(2):
                System.out.println("Please enter your staff email");
                String StaffEmail = scan.nextLine();
                System.out.println("Please enter your staff password");
                String StaffPassword = scan.nextLine();

                UserID = ExtractUserName(StaffEmail);

                System.out.println("USERID: " + UserID + " password: " + StaffPassword);
                return staffLogin(staffList, UserID, StaffPassword);

            
			}
			
		}while(choice<3);
		
		System.out.println("Thank you for using NTU's CAM service."
						+ "\nWe Hope to see you again soon.");	
		
		scan.close();
        User user = new User("nil", "nil", "nil", Faculty.NTU);

        return user;
    }

    public static String ExtractUserName(String Email)
    {
        int EndIndex = Email.indexOf("@");
        if (EndIndex != -1) 
        {
            return(Email.substring(0, EndIndex));     
        }
        else
        { 
            return("Invalid Email.");
        }
    }

    public static Student studentLogin(StudentList studentList, String UserID, String StudentPassword){
        Scanner scan = new Scanner(System.in);
        int sizeStudent = StudentTextDB.getSize();
        for(int i =0; i < sizeStudent; i++)
                {
                    Student student = (Student) studentList.getFromList(i);

                    System.out.println(student.getUserID());

                    if(UserID.equals(ExtractUserName(student.getUserID())) 
                    && StudentPassword.equals(student.getPassword()))
                    {
                        System.out.println("Username and Password accepted!");
                        if(student.getPassword().equals("password")){

                            System.out.println("Your password has not been changed"
                            + "\nWould you like to change your password? (Y/N)");
            
                            if(scan.nextLine().toUpperCase().equals("Y")){
                                changeStudentPassword(student);
                            }
                        }
                        return student;
                    }
                   
                }
        return null;
                
    }

    public static Staff staffLogin(StaffList staffList, String UserID, String StaffPassword){
        Scanner scan = new Scanner(System.in);
        int sizeStaff = StaffTextDB.getSize();
        for(int i =0; i < sizeStaff; i++)
                {
                    Staff staff = (Staff) staffList.getFromList(i);

                    System.out.println(staff.getUserID());

                    if(UserID.equals(ExtractUserName(staff.getUserID())) 
                    && StaffPassword.equals(staff.getPassword()))
                    {
                        System.out.println("Username and Password accepted!");
                        if(staff.getPassword().equals("Password")){

                            System.out.println("Your password has not been changed"
                            + "\nWould you like to change your password? (Y/N)");
            
                            if(scan.nextLine().toUpperCase().equals("Y")){
                                changeStaffPassword(staff);
                            }
                        }
                        return staff;
                    }
                   
                }
        return null;
                
    }

    public static void changeStudentPassword(Student student){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter new password");
        String StudentPassword = scan.nextLine();
        student.changePassword(StudentPassword);
        StudentTextDB.updatePassword(student);
        System.out.println("Successfully updated password!");
    }

    public static void changeStaffPassword(Staff staff){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter new password");
        String Staffpassword = scan.nextLine();
        staff.changePassword(Staffpassword);
        StaffTextDB.updatePassword(staff);
        System.out.println("Successfully updated password!");
    }



    
}