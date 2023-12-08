<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
        <link rel="stylesheet" type="text/css" href="css/style1.css">
    <script src="scripts/script.js"></script>
    <title>E-Commerce Register Page</title>
</head>
<body>
    <div class="container">
        <div class="divFormReg">
    <h3>
        <p class="label">Enter your information below to register</p>
    </h3><br>
    <s:form id="reg" action="register" method="POST">
        <label class= "labelReg"> Enter your username:</label>
        <input class= "inputReg" type="text" name="username" id="username" class="textbox" placeholder="Username" required/><br>
        <label class= "labelReg">Enter your email:</label>
        <input class= "inputReg" type="text" name="email" id="email" class="textbox" placeholder="Email" required/><br>
        <label class= "labelReg">Enter your first name:</label>
        <input class= "inputReg"  type="text" name="firstName" id="firstName" class="textbox" placeholder="First Name" required/><br>
        <label class= "labelReg">Enter your last name:</label>
        <input class= "inputReg"  type="text" name="lastName" id="lastName" class="textbox" placeholder="Last Name" required/><br>
        <label class= "labelReg">Enter your password:</label>
        <input class= "inputReg" type="password" name="password" id="user_password" class="textbox" placeholder="Password" required/><br>
        <label class= "labelReg">Enter your password confirmation:</label>
        <input class= "inputReg"  type="password" name="passwordConf" id="user_password_conf" class="textbox" placeholder="Password Confirmation" required/><br>
        <br><input type="submit" class="btn" onclick="setPasswordType()"/>
    </s:form>
            <br>Show Password: <input type="checkbox" onclick="showPasswordToggle()" /><br><br>
            <b>Password Requirements:</b><br>
            - Minimum 8 Characters<br>
            - Maximum 20 Characters<br>
            - No Whitespaces/Blank Input Field or Fields Permitted<br><br>

            <!-- Alternative Button to redirect to login page if already registered-->
            If you are already registered, go to the login page:<br><br>
            <input type="button" value="Login Page" onclick="window.location.href='login.jsp'" />
        </div>
        <div id="errMsgDiv"> </div>
    </div>

    <script>
        window.onload = function() {
            var errMsg = document.getElementById('errMsgDiv');
            if ('<s:property value="errorMessage" />' == null || '<s:property value="errorMessage" />' === '' ) {
                errMsg.style.visibility = 'hidden';
            } else {
                errMsg.style.visibility = 'visible';
                errMsg.classList.add('errMsgBox');
                errMsg.innerHTML = '<s:property value="errorMessage" />'
            }
        };
    </script>
</body>
</html>
