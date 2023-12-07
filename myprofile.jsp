<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="css/style1.css">
    <script src="scripts/script.js"></script>
    <title>E-Commerce Website</title>
</head>
    <header>
        <h1>eCommerce Website</h1>
    </header>

<body>
<nav>
    <div >
        <a href="homepage.jsp">Home</a>
        <a href="myprofile.jsp">My Profile</a>
        <a href="#">View Items</a>
        <a href="additem.jsp">Add Item</a>
        <a onclick="viewAllUsersForm()">View All Users</a>
    </div>
    <div>
        <s:form id="logout" action="logout" method="POST">
            <button class="btn" onclick="logout()">Logout</button>
        </s:form>
    </div>
</nav>

<div class="container">
    <h1>Hi <s:property value="#session.currentUser.username" />, you are currently logged in!</h1>
    <h2>My Profile: <s:property value="#session.currentUser.username" /></h2>
    <div class="content-row">
        <div class="content-display">
            <s:property value="#session.currentUser.username" /><br>
            <s:property value="#session.currentUser.email" /><br>
            <s:property value="#session.currentUser.firstName" /><br>
            <s:property value="#session.currentUser.lastName" /><br>
            <s:property value="#session.currentUser.dateJoined" /><br>
        </div>
    </div>
</div>

<section>
</div>
     </body>
      <!--Forms -->
<s:form action="viewAllUsers" method="POST" id="allUsers"></s:form>
 </html>
 <script>
        window.onload = function () {
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