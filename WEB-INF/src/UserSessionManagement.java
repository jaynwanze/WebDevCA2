import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class UserSessionManagement extends ActionSupport implements SessionAware {
	private String username;
	private String password;
	private UserDAO user;
	private Map<String, Object> session;
	private String errorMessage;
	private String logoutMessage;


	public UserSessionManagement() {

	}

	public String login() {
		String lowercaseUsername = username.toLowerCase();
		String result = "FAILURE";
		setLogoutMessage(null);//ensure logout message doesnt pop up while logging in

		Connection connection = null;
		;
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
		if (!(user.validateBlankInputLogin(lowercaseUsername, password))) {
			setErrorMessage("Error Processing Request: No Whitespaces/Blank Input Fields");
			return result;
		} else if (!(user.validatePassword(password))) {
			setErrorMessage(
					"Error Processing Request: Password/Password Confirmation Must Be More Than 8 And Less Than 20 Characters");
			return result;
		} else if (!(user.checkUsernameExists(lowercaseUsername))) {
			setErrorMessage("Error Processing Request: Username Does Not Exist Within Database");
			return result;
		} else if (!(user.checkPasswordMatches(lowercaseUsername, password))) {
			setErrorMessage("Error Processing Request: Password Is Incorrect");
			return result;
		} else if (user.checkPasswordMatches(lowercaseUsername, password)) {
			setErrorMessage(null);
			session.put("currentUser", username);
			return result = "SUCCESS";
		}
		// Default error message
		setErrorMessage("Error Processing Request: Not Able To Add User Within Database");
		// Reset user input
		setUsername(null);
		setPassword(null);

		return result;

	}

	public String logout() {
		String result = "FAILURE";
		session.clear();//clear session details
		if (session.isEmpty()) {
			setErrorMessage(null);
			setLogoutMessage("You Have Sucessfully Been Logged Out");
			return result = "SUCCESS";
		}
		setErrorMessage("Error Processing Request: Unable to Log User Out Of Session");
		return result;//show some type of error message
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

	@Override
	public void setSession(Map map) {
		session = map;

	}

	public Map<String, Object> getSession() {

		return session; // change to true
	}
}
