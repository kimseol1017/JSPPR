<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import = "product.ProductDTO" %>

<%
	//session 변수에 담긴 값을 불러옴 : 서버의 RAM 
	ProductDTO product = new ProductDTO(); 
	
	//session 변수의 값을 가지고 올때 다운 캐스팅이 필요하다. 
	product = (ProductDTO) session.getAttribute("product"); 

%>

 <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 상세페이지</title>
</head>
<body>
	<center>
	<h1> 상품 상세 페이지 </h1>
	<hr>
	<br><br>
	<form method ="post" action = "updateProduct.pr">
		<input type="hidden" name= "p_id" value = "<%= product.getP_id() %>">
		
		<table border= "1" width = "700px" cellpadding="5px">
		<tr>
					<td bgcolor="orange" align="center">상품명</td>
					<td><input type="text" name="name"
						value="<%= product.getName() %>"></td>
				</tr>
				<tr><td bgcolor="orange" align = "center"> 가격 </td> 
				<td> <input type="text" name = "price" value="<%=product.getPrice() %>"></td>
			</tr>
			<tr><td  bgcolor="orange" align = "center"> 상품설명 </td> 
				<td> <textarea name="content" rows="10" cols="70"> <%=product.getContent() %> </textarea></td>
			</tr>
			<tr><td bgcolor="orange" align = "center"> 등록일 </td> 
				<td> <%=product.getRegdate() %></td>
			</tr>
			<tr><td colspan = "2" align = "center"> <input type="submit" value="상품 수정하기">  </td> 

			</tr>
		
		</table>
		
	</form>
	<a href = "deleteProduct.pr?id=<%=product.getP_id() %>" >
		상품 삭제 
	</a>
	
	<p /> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
	<p /> <a href= "getProductList.pr"> 상품 목록 </a>
	<p /> <a href= "insertProduct.jsp"> 상품 등록 </a>
	
	</center>
</body>
</html>