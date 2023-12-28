<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 회원가입</title>
</head>
<body>
<center> 
	<h1> 관리자 등록 </h1>
	<hr> 
	
	<form method = "post" action ="insertAUsers.us"> 
		<table border="1" cellpadding = "10" cellspacing = "0">
			<tr> <td bgcolor="blue"> *ID </td> 
				<td> <input type = "text" name = "m_id"> </td>
			</tr> 
			<tr> <td  bgcolor="blue"> *비밀번호 </td> 
				<td> <input type = "text" name = "password" size ="10"> </td>
			</tr>
			<tr> <td  bgcolor="blue"> *전화번호 </td> 
				<td> <input type = "text" name = "phone" size ="10"> </td>
			</tr>
			<tr> <td  bgcolor="blue"> EMAIL </td> 
				<td> <input type = "text" name = "email" size ="10"> </td>
			</tr>
			<tr> <td  bgcolor="blue"> 주소 </td> 
				<td> <input type = "text" name = "addr" size ="10"> </td>
			</tr>
			<tr> <td  bgcolor="blue"> 권한명 </td> 
				<td> <input type = "text" name = "role" size ="10"> </td>
			</tr>
			<tr> <td colspan ="2" align = "center">
					<input type="submit" value="등록"> 
			 	</td> 
				
			</tr>
		
		</table>
	</form>
	
		<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>

	
	
	
	</center>
</body>
</html>