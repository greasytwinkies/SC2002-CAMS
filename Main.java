public class Main {

    public static void main(String[] args) {


        CampList campList = new CampList("campList");
        int logout = 0;

        do{
        Object user = LoginPage.login();

        if( user instanceof Student){
            Student student = (Student) user;
            StudentMenuController studentMenuController = new StudentMenuController();
            logout = studentMenuController.StudentMenuControl(student, campList);

        }
        else {
            Staff staff = (Staff) user;
            StaffMenuController staffMenuController = new StaffMenuController();
            logout =  staffMenuController.StaffMenuControl(staff, campList);
        }
        }while(logout == 1);
}
}
    


