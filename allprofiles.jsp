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
      <h2>User List</h2>
<section>

<s:form id="viewProfile" action="viewUserProfile" method="POST">
    <table>
        <th> Users</th>
        <s:iterator value="users">
            <tr>
                <td>
                    <div class="clickable" onclick="viewProfileForm()">
                        <s:property/>
                        <input type="hidden" name="username" value='<s:property />' />
                    </div>
                </td>
            </tr>
        </s:iterator>
    </table>
</s:form>

<section>
<div id="errMsgDiv"> </div>
</div>
     </body>
     <!--Forms -->
<s:form action="viewAllUsers" method="POST" id="allUsers"></s:form>
<s:form action="viewUserProfile" method="POST" id="userProfile"><input type ="hidden" name =""></s:form>


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

