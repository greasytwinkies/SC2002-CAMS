

public class User {
    private String name;
    private String userID;
    private String password;
    private Faculty facultyInformation;
    
    
    

    User(String name, String userID, String password, Faculty facultyInformation){
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.facultyInformation = facultyInformation;
    }

    //getters 
    public String getName(){
        return name;
    }
    public String getUserID(){
        return userID;
    }
    public String getPassword(){
        return password;
    }
    public Faculty getFacultyInformation(){
        return facultyInformation;
    }

    public void changePassword(String newPassword){
        this.password = newPassword;
    }

}