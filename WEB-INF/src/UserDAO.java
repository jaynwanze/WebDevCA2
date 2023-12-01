import java.util.ArrayList;

public class UserDAO {
    private String errorMessage;

    private ArrayList<String> users = new ArrayList<String>();

    public UserDAO() {

    }

    public boolean checkUsername(String username) {
        // return false if wrong and set error message to be displayed on failure.jsp

        return true;
    }

    public String validatePasswords(String password, String passwordConf) {
        // return false if wrong and set error message to be displayed on failure.jsp
        String result = "";

        return result;

    }

    public String validatePassword(String password) {
        // return false if wrong and set error message to be displayed on failure.jsp
        String result = "";

        return result;
    }

    public boolean addUserToDB(String username, String password) {
        // return false if wrong and set error message to be displayed on failure.jsp

        return true;
    }

    public ArrayList<String> getUsers() {
        // users = getUsersfromdb()
        return users;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
