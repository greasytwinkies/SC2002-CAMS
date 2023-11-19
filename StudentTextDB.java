//read and write textDB (student)
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


public class StudentTextDB {
	
	private static int size;
	public static final String SEPARATOR = "|";

    // an example of reading
	public static ArrayList readStudents(String filename) throws IOException {
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
				String password = star.nextToken().trim();
				//int  faculty = Integer.parseInt(star.nextToken().trim()); // third token
				// create Professor object from file data
				Student student = new Student(name, email,password, faculty);
				// add to Professors list
				alr.add(student) ;
			}
			return alr ;
	}

	public static ArrayList readStudentsExcept(String filename, Student student) throws IOException {
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
				String password = star.nextToken().trim();
				//int  faculty = Integer.parseInt(star.nextToken().trim()); // third token
				// create Professor object from file data

				//add all student except
				if(student.getUserID().equals(email)){
					continue;
				}
				Student readStudent = new Student(name, email,password, faculty);

				// add to Professors list
				alr.add(readStudent) ;
			}
			return alr ;
	}

  // an example of saving
public static void saveStudent(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				Student student = (Student)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(student.getName().trim());
				st.append(SEPARATOR);
				st.append(student.getUserID().trim());
				st.append(SEPARATOR);
				st.append(student.getFacultyInformation());
				st.append(SEPARATOR);
				st.append(student.getPassword());
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

  //create a new student
  public static void createStudent(Student createdStudent){
	StudentTextDB txtDB = new StudentTextDB();
    	String filename = "studentlist.txt" ;
		try {
			// read file containing student records.
			List al = StudentTextDB.readStudents(filename);
			// al is an array list containing Student objs
			al.add(createdStudent);
			// write Student record/s to file.
			StudentTextDB.saveStudent(filename, al);
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

  public static void populateStudentList(StudentList studenList){
		StudentTextDB txtDB = new StudentTextDB();
    	String filename = "studentlist.txt" ;
		try {
			// read file containing Professor records.
			ArrayList al = StudentTextDB.readStudents(filename) ;
			for (int i = 0 ; i < al.size() ; i++) {
					Student student = (Student)al.get(i);
					// System.out.println("Name " + student.getName() );
					// System.out.println("Email " + student.getUserID() );
					// System.out.println("faculty " + student.getFacultyInformation());
					// System.out.println("password " + student.getPassword());
					Student s = new Student(student.getName(),student.getUserID(),student.getPassword(), student.getFacultyInformation());
					studenList.addToList(s);
					size++;
			}
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
  }

  public static int getSize(){
	return size;
  }

  public static void updatePassword(Student student){
		StudentTextDB txtDB = new StudentTextDB();
    	String filename = "studentlist.txt" ;
		try {
			List al = StudentTextDB.readStudentsExcept(filename, student) ;
			al.add(student);
			// write Professor record/s to file.
			StudentTextDB.saveStudent(filename, al);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}

  }

// public static void main(String[] aArgs)  {
//     	StudentTextDB txtDB = new StudentTextDB();
//     	String filename = "studentlist.txt" ;
// 		try {
// 			// read file containing Professor records.
// 			List al = StudentTextDB.readStudents(filename) ;
// 			for (int i = 0 ; i < al.size() ; i++) {
// 					Student student = (Student)al.get(i);
// 					System.out.println("Name " + student.getName() );
// 					System.out.println("Email " + student.getUserID() );
// 					System.out.println("faculty " + student.getFacultyInformation());
// 					System.out.println("password " + student.getPassword());
// 			}
// 			Student s1 = new Student("daddy sean","DAD@ntu.edu.sg","im so hot", "MACS");
// 			// al is an array list containing Professor objs
// 			al.add(s1);
// 			// write Professor record/s to file.
// 			StudentTextDB.saveStudent(filename, al);
// 		}catch (IOException e) {
// 			System.out.println("IOException > " + e.getMessage());
// 		}
//   }
}