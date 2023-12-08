<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/style1.css">
        <script src="scripts/script.js"></script>
    <title>eCommerce Website</title>
</head>

<body>

    <header>
        <h1>eCommerce Website</h1>
    </header>
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
     </div>
  <!-- Homepage Content Section -->
<section class="homepage-content">
    <div class="content-row">
     <!-- Column 1: View All Bids -->
        <div class="content-column">
            <h2>View My bids</h2>
        </div>
        <!-- Column3: View All Items for Sale -->
        <div class="content-column">
            <h2>View All Items for Sale</h2>
            
        </div>
          <div class="content-display-profile">
  <div class = "clickable" onclick = "window.location.href='myprofile.jsp';"><h2>My Profile</h2> </div>     
                     <table class = "table-profile">
                          <th> Username</th>
                            <tr><td><s:property value="#session.currentUser.username" /><br></td></tr>
                            <th> Email</th>
                            <tr><td> <s:property value="#session.currentUser.email" /><br></td></tr>
                            <th> First Name</th>
                            <tr><td> <s:property value="#session.currentUser.firstName" /><br></td></tr>
                            <th>Last Name</th>
                            <tr><td>  <s:property value="#session.currentUser.lastName" /><br></td></tr>
                            <th> Date/Time Joined</th>
                            <tr><td> <s:property value="#session.currentUser.dateJoined" /><br></td></tr>
                     </table>
                   </div>         
                </div>
</section>    
        <div class="container">
        <div id="errMsgDiv"> </div>
        </div>
    <footer>
        &copy; WebDevCA2 - Jamin Nwanze
    </footer>

</body>

<!--Forms -->
<s:form action="viewAllUsers" method="POST" id="allUsers"></s:form>
<s:form action="viewMyProfile" method="POST" id="myProfile"></s:form>


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
             // Set a timeout to hide the message after 10 seconds
        setTimeout(function() {
            errMsg.style.visibility = 'hidden';
        }, 10000); // 10000 milliseconds = 10 seconds
        };

  </script>
</html>
