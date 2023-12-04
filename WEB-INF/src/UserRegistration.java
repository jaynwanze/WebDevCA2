import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserRegistration {
    private String username;
    private String password;
    private String passwordConf;
    private String errorMessage;
    private UserDAO user;

    public UserRegistration() {

    }

    public String register() {
        String lowercaseUsername = username.toLowerCase();
        String result = "FAILURE";
        Connection connection = null;
        // Create connection to database
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC", "root", "root");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Create new userDAO instance
        user = new UserDAO(connection);

        // Validation
        if (!(user.validateBlankInputReg(lowercaseUsername, password, passwordConf))) {
            setErrorMessage("Error Processing Request: No Whitespaces/Blank Input Fields");
            return result;
        } else if (user.validatePasswords(password, passwordConf).equals("IncorrectLength")) {
            setErrorMessage(
                    "Error Processing Request: Password/Password Confirmation Must Be More Than 8 And Less Than 20 Characters");
            return result;
        } else if (user.validatePasswords(password, passwordConf).equals("PasswordsDontMatch")) {
            setErrorMessage("Error Processing Request: Password And Password Confirmation Do Not match");
            return result;
        } else if (user.validatePasswords(password, passwordConf).equals("Error")) {
            setErrorMessage("Error Processing Request: Error Checking Passwords");
            return result;
        } else if (user.checkUsernameExists(lowercaseUsername)) {
            setErrorMessage("Error Processing Request: Username Already Exists");
            return result;
        } else if (user.addUserToDB(lowercaseUsername, password)) {
            setErrorMessage(null);
            return result = "SUCCESS";
        }
        //Default error message
        setErrorMessage("Error Processing Request: Not Able To Add User Within Database");
        //Reset user input
        setUsername(null);
        setPassword(null);
        setPasswordConf(null);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConf() {
        return passwordConf;
    }

    public void setPasswordConf(String passwordConf) {
        this.passwordConf = passwordConf;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
