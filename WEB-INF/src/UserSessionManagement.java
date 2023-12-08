import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class UserSessionManagement extends ActionSupport implements SessionAware {
	private String username;
	private String password;
	private UserDAO userDAO;
	private Map<String, Object> session;
	UserProfileActions userProfileActions;
	UserItemActions userItemActions;
	UserBidActions userBidActions;

	private String errorMessage;
	private String logoutMessage;
	private String successMessage;

	public UserSessionManagement() {

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

	public String login() {
		String lowercaseUsername = username.toLowerCase();
		String result = "FAILURE";

		// Create connection to database
		Connection connection = getConnection();
		// Create new userDAO instance
		userDAO = new UserDAO(connection);

		// Validation
		if (!(userDAO.validateBlankInputLogin(lowercaseUsername, password))) {
			setErrorMessage("Error Processing Request: No Whitespaces/Blank Input Fields");
			return result;
		} else if (!(userDAO.validatePassword(password))) {
			setErrorMessage(
					"Error Processing Request: Password/Password Confirmation Must Be More Than 8 And Less Than 20 Characters");
			return result;
		} else if (!(userDAO.checkUsernameExists(lowercaseUsername))) {
			setErrorMessage("Error Processing Request: Username Does Not Exist Within Database");
			return result;
		} else if (!(userDAO.checkPasswordMatches(lowercaseUsername, password))) {
			setErrorMessage("Error Processing Request: Password Is Incorrect");
			return result;
		} else if (userDAO.checkPasswordMatches(lowercaseUsername, password)) {
			setErrorMessage(null);
			setSuccessMessage(null);
			User user = userDAO.getUserProfile(lowercaseUsername);
			session.put("currentUser", user);
			return result = "SUCCESS";
		}
		// Default error message
		setErrorMessage("Error Processing Request: Not Able To Add User Within Database");
		setSuccessMessage(null);

		// Reset user input
		setUsername(null);
		setPassword(null);

		return result;

	}

	public String logout() {
		String result = "FAILURE";
		session.clear();// clear session detail

		if (session.isEmpty()) {
			setErrorMessage(null);
			setLogoutMessage("You Have Sucessfully Been Logged Out");
			return result = "SUCCESS";
		}
		setErrorMessage("Error Processing Request: Unable to Log User Out Of Session");
		return result;// show some type of error message
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

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getLogoutMessage() {
		return logoutMessage;
	}

	public void setLogoutMessage(String logoutMessage) {
		this.logoutMessage = logoutMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	@Override
	public void setSession(Map map) {
		session = map;

	}
}
