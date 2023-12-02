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

    public boolean validateBlankInputReg(String username, String password, String passwordConf) {
        // if user inputs is blank or has whitespaces
        if (!(username.isBlank()) && !(username.matches(".*\\s.*")) && !(password.isBlank())
                && !(password.matches(".*\\s.*")) && !(passwordConf.isBlank())
                && !(passwordConf.matches(".*\\s.*"))) {
            return true;
        }
        return false;
    }

    public boolean validateBlankInputLogin(String username, String password) {
        // if user inputs is blank or has whitespaces
        if (!(username.isBlank()) && !(username.matches(".*\\s.*")) && !(password.isBlank())
                && !(password.matches(".*\\s.*"))) {
            return true;
        }
        return false;
    }

    public String validatePasswords(String password, String passwordConf) {
        String result = "Error"; // default

        if (password.length() >= 8 && password.length() <= 20 && password.equals(passwordConf)) {
            return result = "";// if password meets requirements
        }
        // Check if password or password confirmation is correct length
        else if (password.length() < 8 || password.length() > 20) {
            // return "IncorrectLength" if password or password confirmation isnt correct
            // length
            return result = "IncorrectLength";
        } else if (!(password.equals(passwordConf))) {
            // Return "PasswordsDontMatch" if password or password confirmation dont match
            return result = "PasswordsDontMatch";
        }
        return result;
    }

    public boolean validatePassword(String password) {
        // Check if password or password confirmation is correct length
        if (password.length() >= 8 && password.length() <= 20) {
            return true;
        }
        return false;

    }

    public boolean checkUsernameExists(String username) {
        PreparedStatement checkIfUsernameIsTaken = null;
        ResultSet rs = null;
        try {
            checkIfUsernameIsTaken = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            checkIfUsernameIsTaken.setString(1, username);
            rs = checkIfUsernameIsTaken.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            if (rs.next()) {
                rs.close();
                checkIfUsernameIsTaken.close();
                return true; // Username already exists

            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (checkIfUsernameIsTaken != null)
                    checkIfUsernameIsTaken.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false; // Username is not taken
    }

    public boolean checkPasswordMatches(String username, String password) {
        PreparedStatement checkUserLoginDetails = null;
        ResultSet rs = null;
        try {
            // Prepared statement to check to if user and password match
            checkUserLoginDetails = connection
                    .prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            checkUserLoginDetails.setString(1, username);
            checkUserLoginDetails.setString(2, password);
            // Executes sql query tocheck if username already exists /stores results in rs
            rs = checkUserLoginDetails
                    .executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            // Checks if resultset is returned
            if (rs.next()) {
                rs.close();
                checkUserLoginDetails.close();
                return true;// User Exists
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (checkUserLoginDetails != null)
                    checkUserLoginDetails.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean addUserToDB(String username, String password) {
        PreparedStatement createUser = null;
        try {
            // Execute query to create a new user within
            // database
            createUser = connection.prepareStatement(
                    "INSERT into users "
                            + "(username, password)" + " VALUES (?, ?)");
            // Pass in the values as paramaters into sql statement
            createUser.setString(1, username);
            createUser.setString(2, password);

            int rowsUpdated = createUser.executeUpdate();
            if (rowsUpdated > 0) {
                createUser.close();
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            try {

                if (createUser != null)
                    createUser.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
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
