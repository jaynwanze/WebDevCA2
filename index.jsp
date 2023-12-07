<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="scripts/script.js"></script>
    <title>E-Commerce Register Page</title>
</head>
<body>
    <div class="container">
        <div class="titlebox">
            <h1>E-Commerce Register Page</h1>
        </div>
        <div class="divForm">
            <h3>
                <p class="label">Enter your user and password below to be authorized</p>
            </h3>
            <s:form id="reg" action="register" method="POST">
                <s:textfield name="username" label="Enter your username" />
                <s:textfield name="email" label="Enter your email" />
                <s:textfield name="firstName" label="Enter your first name" />
                <s:textfield name="lastName" label="Enter your last name" />
                <s:password name="password" label="Enter your password" id="user_password" />
                <s:password name="passwordConf" label="Enter your password confirmation" id="user_password_conf" />
                <s:submit onclick="setPasswordType()"/>
            </s:form>
            Show Password: <input type="checkbox" onclick="showPasswordToggle()" /><br>

            <br><b>Password Requirements:</b><br>
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
