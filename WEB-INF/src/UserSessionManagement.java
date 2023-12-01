import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class UserSessionManagement extends ActionSupport implements SessionAware {
	private String username;
	private String password;
	private UserDAO user = new UserDAO();
	private Map<String, Object> session;
	private String errorMessage;


	public UserSessionManagement() {

	}

	public String login() {
		user = new UserDAO();
		String result = "FAILURE";

		// if correct(user.checkUsername(username)) {
		session.put("currentUser", username);
		session.put("errorMsg", null);

		result = "SUCCESS";
		// }

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
	public Map<String, Object>  getSession() {
     
        return session; // change to true
    }
}
