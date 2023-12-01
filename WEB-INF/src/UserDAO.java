import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection; // Database connection

    private String errorMessage;
    private ArrayList<String> users = new ArrayList<String>();

    public UserDAO(Connection connection) {
        // Connection to database
        this.connection = connection;

    }

    public boolean validateBlankInput(String username, String password, String passwordConf) {
        // if user inputs is blank or has whitespaces
        if ((username.isBlank()) || (username.matches(".*\\s.*")) || (password.isBlank())
                || (password.matches(".*\\s.*")) || (passwordConf.isBlank())
                || (passwordConf.matches(".*\\s.*"))) {
            return false;
        }
        return true;
    }

    public String validatePasswords(String password, String passwordConf) {
        String result = "";

        // Check if password or password confirmation is correct length
        if (password.length() < 8 || password.length() > 20) {
            // return "IncorrectLength" if password or password confirmation isnt correct
            // length
            return "IncorrectLength";
        } else if (!(password.equals(passwordConf))) {
            // Return "PasswordsDontMatch" if password or password confirmation dont match
            return "PasswordsDontMatch";
        }

        return result;

    }

    public boolean validatePassword(String password) {
        // Check if password or password confirmation is correct length
        if (password.length() < 8 && password.length() > 20) {
            // return "IncorrectLength" if password or password confirmation isnt correct
            // length
            return false;
        }
        return true;

    }

    public boolean checkUsername(String username) {
        // connect todb and check if passwords match
        if (!(username.equals("jay"))) {
            // Return "PasswordsMatch" password or password confirmation match
            return false;
        } // retrun "all good"

        return true;
    }

    public boolean checkPassword(String password) {
        // connect todb and check if passwords match
        if (!password.equals("userdbpassword")) {
            return false;
        }
        return true;
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
