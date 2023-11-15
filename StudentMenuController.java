import java.util.Scanner;

public class StudentMenuController {
    Scanner scanner = new Scanner(System.in);

    public void StudentMenuControl(Student student) {
        StudentMenu studentMenu = new StudentMenu();
        studentMenu.printMenu();
        int choice = Integer.valueOf(scanner.nextLine());
    //     do {
    //         switch (choice) {
    //             case 1:
    //                 student.viewAvailableCamps();
    //                 break;
    //             case 2:
    //                 student.viewRegisteredCamps();
    //                 break;
    //             case 3:
    //                 student.registerCamp(null);
    //                 break;
    //             case 4:
    //                 student.submitEnquiry();
    //                 break;
    //             case 5:
    //                 student.viewEnquiries();
    //                 break;
    //             case 6:
    //                 student.editEnquiry();
    //                 break;
    //             case 7:
    //                 student.deleteEnquiry();
    //                 break;
    //             case 8:
    //                 break;
    //         }
    //     } while (choice > 8 || choice < 1);
    }
}