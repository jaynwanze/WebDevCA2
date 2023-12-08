import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;


public class UserProfileActions extends ActionSupport implements SessionAware {

    private ArrayList<String> users = new ArrayList<String>();
    private User user = new User();
    private Map<String, Object> session;
    private UserDAO userDAO;
    private String errorMessage;
    private String username;
    

     public UserProfileActions() {
        
    }


    public Connection getConnection() {
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
        return connection;
    }


    public String viewUserProfile() {
        setErrorMessage(null);
        String result = "FAILURE";
         Connection connection = getConnection();
        // Create new userDAO instance
        userDAO = new UserDAO(connection);
       // Set users object to be called in jsp
        if (userDAO.getUserProfile(username) != null) {
            user = userDAO.getUserProfile(username);
            setUser(user);
            return result = "SUCCESS";
        } else {
            setErrorMessage("Error Processing Request: Cannot Access User Details");
        }
        return result;
        
    }

    public String viewAllUsers() {
        setErrorMessage(null);// clear error message
        String result = "FAILURE";
        Connection connection = getConnection();
        // Create new userDAO instance
        userDAO = new UserDAO(connection);
        
        // Set users list to be called in jsp
        if (userDAO.getUsers() != null) {
            setUsers(userDAO.getUsers());
            return result = "SUCCESS";
        } else {
            setErrorMessage("Error Processing Request: User List Is Empty");
        }
        return result;

    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    @Override
	public void setSession(Map map) {
		session = map;

	}
    public Map<String, Object> getSession() {
		return session; 
	}
}