<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록</title>
</head>
<body>
<center> 
	<h1> 상품 등록 - 로그인 한 사용자만 등록 가능 </h1>
	<hr> 
	
	<form method = "post" action ="insertProduct.pr"> 
		<table border="1" cellpadding = "10" cellspacing = "0">
			<tr> <td bgcolor="orange"> 상품코드(정수입력) </td> 
				<td> <input type = "text" name = "p_id"> </td>
			</tr> 
			<tr> <td bgcolor="orange"> 상품명 </td> 
				<td> <input type = "text" name = "name"> </td>
			</tr> 
			<tr> <td  bgcolor="orange"> 가격(정수입력) </td> 
				<td> <input type = "text" name = "price" size ="10"> </td>
			</tr>
			<tr> <td  bgcolor="orange"> 내용</td> 
				<td> <textarea name="content" cols= "40" rows="10"> </textarea> </td>
			</tr>
			<tr> <td colspan ="2" align = "center">
					<input type="submit" value="상품등록"> 
			 	</td> 
				
			</tr>
		
		</table>
	</form>
	
		<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
		<p /> <a href= "getProductList.pr"> 상품 목록 </a>
	
	
	
	</center>
</body>
</html>