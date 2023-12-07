import java.util.ArrayList;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection; // Database connection

    private String errorMessage;
    private ArrayList<String> users;


    public UserDAO(Connection connection) {
        // Connection to database
        this.connection = connection;

    }

    public boolean validateBlankInputReg(String username, String password, String passwordConf, String email, String firstName, String lastName) {
        // if user inputs is blank or has whitespaces
        if (!(username.isBlank()) && !(username.matches(".*\\s.*")) && !(password.isBlank())
                && !(password.matches(".*\\s.*")) && !(passwordConf.isBlank())
                && !(passwordConf.matches(".*\\s.*")) && !(email.isBlank()) && !(email.matches(".*\\s.*")) && !(firstName.isBlank())
                && !(firstName.matches(".*\\s.*")) && !(lastName.isBlank())
                && !(lastName.matches(".*\\s.*"))) {
            return true;
        }
        return false;
    }

     public boolean validateLettersOnly(String firstName, String lastName) {
        // if user inputs is letters
        if (firstName.matches ("[a-zA-Z]+\\.?") && lastName.matches("[a-zA-Z]+\\.?")) {
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

    public boolean addUserToDB(String username, String email, String firstName, String lastName, String password) {
        PreparedStatement createUser = null;
        try {
            // Execute query to create a new user within
            // database
            createUser = connection.prepareStatement(
                    "INSERT into users "
                            + "(username, email, first_name, last_name, password)" + " VALUES (?, ?, ?, ?, ?)");
            // Pass in the values as paramaters into sql statement
            createUser.setString(1, username);
            createUser.setString(2, email);
            createUser.setString(3, firstName);
            createUser.setString(4, lastName);
            createUser.setString(5, password);

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

    public User getUserProfile(String username) {
        User user;
        PreparedStatement getUserProfile = null;
        ResultSet rs = null;
        try {
            // Prepared statement to check to if user and password match
            getUserProfile = connection
                    .prepareStatement("SELECT username, email, first_name, last_name, created_at FROM users WHERE username = ?");
            getUserProfile.setString(1, username);
            // Executes sql query tocheck if username already exists /stores results in rs
            rs = getUserProfile
                    .executeQuery();
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            if (rs.next()) {
                 String email = rs.getString("email");
                 String firstName =  rs.getString("first_name");
                 String lastName =   rs.getString("last_name");
                 String dateJoined = rs.getTimestamp("created_at").toString();
                user = new User(username ,email , firstName, lastName, dateJoined);
                return user;
            }
            try {
                rs.close();
                getUserProfile.close();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (getUserProfile != null)
                    getUserProfile.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public ArrayList<String> getUsers() {
        users = new ArrayList<String>();

        PreparedStatement getUsersFromDB = null;
        ResultSet rs = null;
        try {
            getUsersFromDB = connection.prepareStatement("SELECT username FROM users");
            rs = getUsersFromDB.executeQuery();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            while (rs.next()) {
                String username = rs.getString("username");
                users.add(username);

            }
            try {
                rs.close();
                getUsersFromDB.close();
                 return users;

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (getUsersFromDB != null)
                    getUsersFromDB.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
