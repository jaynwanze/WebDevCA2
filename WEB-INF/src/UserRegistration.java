public class UserRegistration {
    private String username;
    private String password;
    private String passwordConf;
    private String errorMessage;
    private UserDAO user = new UserDAO();


    public UserRegistration() {

    }

    public String register() {
        String result = "FAILURE";
        //if username or pass or pass is empty
        //setErrorMessage("Error Processing Request: No Whitespaces/Blank Input Fields);
        //return result

        // if user.validatePasswords.equals(passdontmatch)
        // setErrorMessage("Error Processing Request: Passwords Dont match");
        //return result

        // else if user.validatePasswords.equals(passdontmatch)
        // setErrorMessage("Error Processing Request: Passwords Dont match");
         //return result

         // else if  user.validatePasswords.equals(morethan8char)
        // setErrorMessage("Error Processing Request: Password must more than 8 and less than 20");
        //return result;

         //if (!user.checkUsername(username)) {
             //setErrorMessage("Error Processing Request: Username Already Exists");
                     //return result;
        //}
        //else{
            // user.addtodb
           // setErrorMessage(null);
            //return result = "SUCCESS";
        //}

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
