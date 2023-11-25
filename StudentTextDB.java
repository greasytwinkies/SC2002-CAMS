//read and write textDB (student)
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Class to manage all Student objects within the CAMS system. Database of Student objects with their information.
 */
public class StudentTextDB {
	

	/**
	 * size of the number of Student Objects within the data base
	 */
	private static int size;

	/**
	 * Seperator for ease of formatting Student data
	 */
	public static final String SEPARATOR = "|";

    /**
	 * Method to read in a Students information, and then create student object, then add to an array
	 * @param filename Filename where Student data is located
	 * @return an Array of student with all the data
	 * @throws IOException when file name does not exist
	 */
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

	/**
	 * Method to read a filename for students data, and add it to an array except for a specific student
	 * @param filename Name of file being read
	 * @param student Student who is not to be in the list
	 * @return array of student objects with their data fields, except for specified student
	 * @throws IOException if filename does not exist
	 */
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

    /** 
	 * Method to save a Students information into the database
	 * @param filename File for student data to be written into
	 * @param al List of Student objects
	 * @throws IOException if filename does not exist
	 */
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

  /**
   * Method to write a students data into a database file
   * @param fileName Name of the file
   * @param data Students data to be added to the file
   * @throws IOException if file name does not exist
   */
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

  /**
   * Method to create a student and save it within the StudentText Data base
   * @param createdStudent Students information whose being written into the data base
   */
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

  /**
   * Method to read in a file, and return a list of all of its information
   * @param fileName file to be read
   * @return List of information from the file
   * @throws IOException if file does not exist
   */
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

  /**
   * Method to input Student objects from Student list into the Student Text Data Base.
   * @param studenList List of students to be added to the database.
   */
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


  /**
   * Method to get the size of the database (how many student objects)
   * @return size of data base
   */
  public static int getSize(){
	return size;
  }

  /**
   * Method to update a students password
   * @param student Student whose password is to be updated
   */
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
}