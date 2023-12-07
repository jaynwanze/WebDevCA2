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
        <div class="titlebox">
            <h1>E-Commerce Login Page</h1>
        </div>
        <div class="divForm">
            
            <s:form action="login" method="POST" id ="loginForm">
                <label for="username">Enter your username:</label>
                <input type = "text" name="username" id="username"  class="textbox" placeholder="Username" required>
                <label for="user_password">Enter your password:</label>
                <input type = "password" name="password" id="user_password" class="textbox" placeholder="Password" required/>
                <input type = "submit"class= "btn"onclick="setPasswordTypeLogin();">
            </s:form>
           <br> Show Password:<input type="checkbox" onclick="showPasswordToggleLogin()" /><br>

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

    </script>
</body>
</html>








