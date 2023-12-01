

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login User</title>
    </head>
    <body>
        <h1>Well done user <s:property value="#session.currentUser" />, you are sucessfully logged in</h1><br>
        <br>
        <h1>User List</h1>
       <table BORDER=”2″>
       <th> Users </th>
       <s:iterator value = "users">
        <tr><td><s:property/></td></tr>
        </s:iterator> 
                 </table>

       


		 </body>
</html>
