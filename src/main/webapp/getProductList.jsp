<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 <%@ page import="java.util.*" %>
 <%@ page import="product.ProductDTO" %>
 
 <%
 
 List<ProductDTO> productList = new ArrayList<>();
 
 try{
	 
	 productList = (List<ProductDTO>) session.getAttribute("productList");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
</head>
<body>
	<center>
	<h1> 상품 목록 </h1>
	<hr>
	
	<table border="1" width="700px">
	<tr>
		<th bgcolor="blue" width=100px">상품코드</th>
		<th bgcolor="blue" width=200px">상품명</th>
		<th bgcolor="blue" width=150px">가격</th>
		<th bgcolor="blue" width=150px">등록일</th>
	
	</tr>
	
	<%
	for (ProductDTO k : productList){
	%>
	
	<tr>
		<td align="center"><%=k.getP_id() %></td>
		
		<td><a href="getProduct.pr?p_id=<%=k.getP_id() %>"> <%=k.getName() %>
		</a></td>
		
		<td><%=k.getPrice() %></td>
		<td><%=k.getRegdate() %></td>
	</tr>
	
	<%
	}
	
	session.removeAttribute("productList");
 }catch (Exception e){
	 response.sendRedirect("getProductList.pr");
 }
	%>
		
	</table>
	
	<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
	<p /><a href="insertProduct.jsp"> 상품 등록 </a>
	</center>
</body>
</html>