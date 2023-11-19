//read and write textDB (staff)
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class StaffTextDB {
	public static int size;
	public static final String SEPARATOR = "|";

    // an example of reading
	public static ArrayList readStaffs(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				String  name = star.nextToken().trim();	// first token
				String  email = star.nextToken().trim();	// second token
				Faculty faculty = Faculty.valueOf(star.nextToken().trim());
				// Faculty faculty = getFacultyFromString(star.nextToken().trim());
				// 	if (faculty == null) {
    			// // Handle unknown faculty, e.g., skip this entry, log a warning, etc.
    			// 		continue;
				// 	}
				String password = star.nextToken().trim();
				//int  faculty = Integer.parseInt(star.nextToken().trim()); // third token
				// create Professor object from file data
				Staff staff = new Staff(name, email,password, faculty);
				// add to Professors list
				alr.add(staff) ;
			}
			return alr ;
	}

	public static ArrayList readStaffsExcept(String filename, Staff staff) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

				String  name = star.nextToken().trim();	// first token
				String  email = star.nextToken().trim();	// second token
				//Faculty faculty = getFacultyFromString(star.nextToken().trim());
				Faculty faculty = Faculty.valueOf(star.nextToken().trim());
				// 	if (faculty == null) {
    			// // Handle unknown faculty, e.g., skip this entry, log a warning, etc.
    			// 		continue;
				// 	}

				String password = star.nextToken().trim();
				//int  faculty = Integer.parseInt(star.nextToken().trim()); // third token
				// create Professor object from file data

				//add all student except
				if(staff.getUserID().equals(email)){
					continue;
				}
				Staff readStaff = new Staff(name, email,password, faculty);

				// add to Professors list
				alr.add(readStaff) ;
			}
			return alr ;
	}

	//
	// public static Faculty getFacultyFromString(String facultyStr) {
	// 	for (Faculty faculty : Faculty.values()) {
	// 		if (faculty.name().equalsIgnoreCase(facultyStr)) {
	// 			return faculty;
	// 		}
	// 	}
	// 	return null; // or throw an exception or return a default value
	// }
	

  // an example of saving
public static void saveStaff(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				Staff staff = (Staff)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(staff.getName().trim());
				st.append(SEPARATOR);
				st.append(staff.getUserID().trim());
				st.append(SEPARATOR);
				st.append(staff.getFacultyInformation());
				st.append(SEPARATOR);
				st.append(staff.getPassword());
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

  /** Write fixed content to the given file. */
  public static void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }

  public static void createStaff(Staff createdStaff){
	StaffTextDB txtDB = new StaffTextDB();
    	String filename = "stafflist.txt" ;
		try {
			// read file containing student records.
			List al = StaffTextDB.readStaffs(filename);
			// al is an array list containing Student objs
			al.add(createdStaff);
			// write Student record/s to file.
			StaffTextDB.saveStaff(filename, al);
			size++;
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  }

  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }

  public static void populateStaffList(StaffList staffList){
	StaffTextDB txtDB = new StaffTextDB();
	String filename = "stafflist.txt" ;
	try {
		// read file containing Professor records.
		ArrayList al = StaffTextDB.readStaffs(filename) ;
		for (int i = 0 ; i < al.size() ; i++) {
				Staff staff = (Staff)al.get(i);
				// System.out.println("Name " + student.getName() );
				// System.out.println("Email " + student.getUserID() );
				// System.out.println("faculty " + student.getFacultyInformation());
				// System.out.println("password " + student.getPassword());
				Staff s = new Staff(staff.getName(),staff.getUserID(),staff.getPassword(), staff.getFacultyInformation());
				staffList.addToList(s);
				size++;
		}
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}
}
  public static int getSize(){
		return size;
}

  public static void updatePassword(Staff staff){
	StaffTextDB txtDB = new StaffTextDB();
	String filename = "stafflist.txt" ;
	try {
		List al = StaffTextDB.readStaffsExcept(filename, staff) ;
		al.add(staff);
		// write Professor record/s to file.
		StaffTextDB.saveStaff(filename, al);
	}catch (IOException e) {
		System.out.println("IOException > " + e.getMessage());
	}

}

// public static void main(String[] aArgs)  {
//     	StaffTextDB txtDB = new StaffTextDB();
//     	String filename = "stafflist.txt" ;
// 		try {
// 			// read file containing Professor records.
// 			ArrayList al = StaffTextDB.readStaffs(filename) ;
// 			for (int i = 0 ; i < al.size() ; i++) {
// 					Staff staff = (Staff)al.get(i);
// 					System.out.println("Name " + staff.getName() );
// 					System.out.println("Email " + staff.getUserID() );
// 					System.out.println("faculty " + staff.getFacultyInformation());
// 					System.out.println("password " + staff.getPassword());
// 			}
// 			Staff s1 = new Staff("daddy sean","DAD@ntu.edu.sg","im so hot", "MACS");
// 			// al is an array list containing Professor objs
// 			al.add(s1);
// 			// write Professor record/s to file.
// 			StaffTextDB.saveStaff(filename, al);
// 		}catch (IOException e) {
// 			System.out.println("IOException > " + e.getMessage());
// 		}
//   }
}