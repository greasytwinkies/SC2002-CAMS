/**
    Represents an account of a student or staff enrolled in the school.
*/

public class User {

    /**
    * The name of this user.
    */
    private String name;

    /**
    * The account username of this user.
    */
    private String userID;

    /**
    * The password of this account.
    */
    private String password;

    /**
    * The faculty within the school of which this user is enrolled in.
    */
    private Faculty facultyInformation;

    /**
    * Creates a new User with the given name, userID, password and Faculty.
    * @param name This User's name.
    * @param userID This User's account username.
    * @param password This User's account password.
    * @param facultyInformation The school faculty which this User belongs to.
    */
    User(String name, String userID, String password, Faculty facultyInformation){
        this.name = name;
        this.userID = userID;
        this.password = password;
        this.facultyInformation = facultyInformation;
    }

    /**
    * Gets the name of this User.
    * @return This User's name.
    */
    public String getName(){
        return name;
    }

    /**
    * Gets the username of this User's account.
    * @return This User's account username.
    */
    public String getUserID(){
        return userID;
    }


    /**
    * Gets the password of this User's account.
    * @return This user's account password.
    */
    public String getPassword(){
        return password;
    }

    /**
    * Gets the faculty information of this User.
    * @return This User's faculty.
    */
    public Faculty getFacultyInformation(){
        return facultyInformation;
    }


    /**
    * Changes the password of this User's account.
    * @param newPassword This User's new password.
    */
    public void changePassword(String newPassword){
        this.password = newPassword;
    }

}