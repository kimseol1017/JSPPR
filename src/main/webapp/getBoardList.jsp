<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 <%@ page import="java.util.*" %>
 <%@ page import="board.BoardDTO" %>
 
 <%
 
 List<BoardDTO> boardList = new ArrayList<>();
 
 try{
	 
 	boardList = (List<BoardDTO>) session.getAttribute("boardList");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
</head>
<body>
	<center>
	<h1> 글 목록 </h1>
	<hr>
	
	<table border="1" width="700px">
	<tr>
		<th bgcolor="blue" width=100px">번호</th>
		<th bgcolor="blue" width=200px">제목</th>
		<th bgcolor="blue" width=150px">작성자</th>
		<th bgcolor="blue" width=150px">등록일</th>
		<th bgcolor="blue" width=100px">조회수</th>
	
	</tr>
	
	<%
	for (BoardDTO k : boardList){
	%>
	
	<tr>
		<td align="center"><%=k.getId() %></td>
		
		<td><a href="getBoard.do?id=<%=k.getId() %>"> <%=k.getM_title() %>
		</a></td>
		
		<td><%=k.getM_write() %></td>
		<td><%=k.getRegdate() %></td>
		<td><%=k.getCnt() %></td>
	</tr>
	
	<%
	}
	
	session.removeAttribute("boardList");
 }catch (Exception e){
	 response.sendRedirect("getBoardList.do");
 }
	%>
		
	</table>
	
	<br> <a href="http://localhost:8181/JSP_MY_PROJ"> 홈으로 </a>
	<p /><a href="insertBoard.jsp"> 새 글쓰기 </a>
	</center>
</body>
</html>