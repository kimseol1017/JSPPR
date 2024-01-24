<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%	String sessionRole = (String) session.getAttribute("role");  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 실습</title>
</head>
<body>
	<!-- 모든 웹페이지는 index.jsp 에서 링크로 이루어져 있다.  -->
	<% if(sessionRole.equals("admin")){ %>
	<h2>관리자 로그인</h2>
	<hr>
	<a href="admin.jsp"> 관리자 페이지 </a>
	<p />
	<a href="insertProduct.jsp"> 상품 등록 페이지 (관리자만 접근) </a>
	<p />
	<a href="getProductList.pr"> 상품 리스트 페이지 (관리자만 접근) </a>
	<p />
	<a href="getUsersList.us"> 사용자 리스트 페이지 </a>
	<p />
	<a href="signIn.jsp"> 사용자 등록 </a>
	<p />
	<a href="AdminSignIn.jsp"> 관리자 사용자 등록 </a>
	<p />
	<a href="LoginForm.jsp"> 로그인 페이지 </a>
	<p />
	<% }else if(sessionRole.equals(null)){%>
	<h1>로그인 후 이용하세요</h1>
	<p />
	<a href="LoginForm.jsp"> 로그인 페이지 </a>
	<p />
	<% }else{%>
	<h2>index 페이지</h2>
	<hr>
	<br>
	<a href="getBoardList.do"> 게시판 리스트 페이지 </a>
	<p />
	<a href="insertBoard.jsp"> 게시판 글쓰기 </a>
	<p />

	<a href="getUsersList.us"> 사용자 리스트 페이지 </a>
	<p />
	<a href="signIn.jsp"> 사용자 등록 </a>
	<p />
	<a href="AdminSignIn.jsp"> 관리자 사용자 등록 </a>
	<p />
	<a href="LoginForm.jsp"> 로그인 페이지 </a>
	<p />
	
<%} %>

</body>
</html>