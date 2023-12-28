<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 <%@ page import="java.util.*" %>
 <%@ page import="user.UserDTO" %>
 
 <%
 
 List<UserDTO> userList = new ArrayList<>();
 
 try{
	 
 	userList = (List<UserDTO>) session.getAttribute("userList");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 리스트</title>
</head>
<body>
	<center>
	<h1> 사용자 리스트 </h1>
	<hr>
	
	<table border="1" width="700px">
	<tr>
		<th bgcolor="blue" width=200px">ID</th>
		<th bgcolor="blue" width=200px">EMAIL</th>
		<th bgcolor="blue" width=200px">주소</th>
		<th bgcolor="blue" width=150px">등록일</th>
	</tr>
	
	<%
	for (UserDTO k : userList){
	%>
	
	<tr>
		<td align="center"><%=k.getM_id() %></td>
		</a></td>
		
		<td><%=k.getM_id() %></td>
		<td><%=k.getEmail() %></td>
		<td><%=k.getRegdate() %></td>
	</tr>
	
	<%
	}
	
	session.removeAttribute("userList");
 }catch (Exception e){
	 response.sendRedirect("getUsersList.us");
 }
	%>
		
	</table>
	
	<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
	</center>
</body>
</html>