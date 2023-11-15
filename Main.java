public class Main {
    public static void main(String[] args) {

        Object user = LoginPage.login();
        CampList campList = new CampList("campList");

        if( user instanceof Student){
            Student student = (Student) user;
            StudentMenuController studentMenuController = new StudentMenuController();
            studentMenuController.StudentMenuControl(student);
        }
        else {
            Staff staff = (Staff) user;
            StaffMenuController staffMenuController = new StaffMenuController();
            staffMenuController.StaffMenuControl(staff, campList);

        }
        }
        
        
}
    


