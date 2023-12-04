<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <title>E-Commerce Register Page</title>
</head>
<body>
    <div class="container">
        <div class="titlebox">
            <h1>E-Commerce Login Page</h1>
        </div>
        <div class="divForm">
            <h3>
                <p class="label">Enter your user and password below to be authorized</p>
            </h3>
            <s:form action="login" method="POST">
                <s:textfield name="username" label="Enter your username" />
                <s:password name="password" label="Enter your password" id="user_password" />
                <s:submit onclick="setPasswordType()"/>
            </s:form>
            Show Password: <input type="checkbox" onclick="showPasswordToggle()" /><br>

            <br><b>Password Requirements:</b><br>
            - Minimum 8 Characters<br>
            - Maximum 20 Characters<br>
            - No Whitespaces/Blank Input Field or Fields Permitted<br><br>

            <!-- Alternative Button to redirect to login page if already registered-->
            If you don't have an account, go to the registration page:<br><br>
            <input type="button" value="Registration Page" onclick="window.location.href='index.jsp'" />
        </div>
        <div id="errMsgDiv"> </div>
        <div id="logoutMsgDiv"> </div>
    </div>

    <script>
        window.onload = function() {
            var errMsg = document.getElementById('errMsgDiv');
            if ('<s:property value="errorMessage" />' == null || '<s:property value="errorMessage" />' === '') {
                errMsg.style.visibility = 'hidden';
            } else {
                errMsg.style.visibility = 'visible';
                errMsg.classList.add('errMsgBox');
                errMsg.innerHTML = '<s:property value="errorMessage" />'
            }
            // Logout Message
             var logoutMsg = document.getElementById('logoutMsgDiv');
                if ('<s:property value="logoutMessage" />' == null || '<s:property value="logoutMessage" />' === '') {
               logoutMsg.style.visibility = 'hidden';
                } else {
        logoutMsg.style.visibility = 'visible';
        logoutMsg.classList.add('logoutMsgBox');
        logoutMsg.innerHTML = '<s:property value="logoutMessage" />';
        // Set a timeout to hide the message after 10 seconds
        setTimeout(function() {
            logoutMsg.style.visibility = 'hidden';
        }, 10000); // 10000 milliseconds = 10 seconds
        };
        }
     

        function showPasswordToggle() {
            var pass = document.getElementById("user_password");

            if (pass.type === "password" ) {
                pass.type = "text";
            } else {
                pass.type = "password";
            }
        }

        function setPasswordType() {
            var pass = document.getElementById("user_password");

            if (pass.type != "password"){
                pass.type = "password";
            }
        }
    </script>
</body>
</html>








