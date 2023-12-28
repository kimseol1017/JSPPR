package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import board.BoardDAO;
import board.BoardDTO;


@WebServlet("*.us")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public UserController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("UTF-8");
		
		String uri = request.getRequestURI(); 
		String path = uri.substring(uri.lastIndexOf("/")); 
		
		if (path.equals("/login.us")) {
			System.out.println("login.us 요청 처리");
			
			String m_id = request.getParameter("m_id");
			String password = request.getParameter("password");
			
			UserDTO dto = new UserDTO();
			dto.setM_id(m_id);
			dto.setPassword(password);
			
			UserDAO dao = new UserDAO();
			
			UserDTO user = new UserDTO();
			
			user = dao.login(dto);
			
			if (user == null) {
				//로그인 실패
				System.out.println("인증 실패");
				response.sendRedirect("LoginForm.jsp");			
			}else {
				System.out.println("인증 성공");
				
				HttpSession session = request.getSession();
				session.setAttribute("m_id", user.getM_id());
				session.setAttribute("role", user.getRole());
				
				response.sendRedirect("LoginForm.jsp");
			}
			
		}else if (path.equals("/insertUsers.us")) {//사용자등록
			System.out.println("insertUsers.us 요청 처리");
			
			String m_id = request.getParameter("m_id");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");
			String role = request.getParameter("role");
			
			UserDTO dto = new UserDTO();
			
			dto.setM_id(m_id);
			dto.setPassword(password);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(addr);
			dto.setRole(role);
			
			
			UserDAO dao = new UserDAO();
			dao.SignIn(dto);
			
			
			response.sendRedirect("signIn.jsp");
			
		}else if (path.equals("/insertAUsers.us")) {//관리자사용자등록
			System.out.println("insertAUsers.us 요청 처리");
			
			String m_id = request.getParameter("m_id");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			String addr = request.getParameter("addr");
			String role = request.getParameter("role");
			
			UserDTO dto = new UserDTO();
			
			dto.setM_id(m_id);
			dto.setPassword(password);
			dto.setPhone(phone);
			dto.setEmail(email);
			dto.setAddr(addr);
			dto.setRole(role);
			
			
			UserDAO dao = new UserDAO();
			dao.AdminSignIn(dto);
			
			
			response.sendRedirect("AdminSignIn.jsp");
			
		}else if (path.equals("/logout.us")) {
			System.out.println("/logout.us 요청 처리 ");
			
			HttpSession session = request.getSession(); 
			
			session.invalidate(); 
		
			response.sendRedirect("LoginForm.jsp"); 
			
			
		}else if (path.equals("/getUsersList.us")) {
			System.out.println("/getUsersList.us 요청");
			
			UserDTO dto = new UserDTO();
			UserDAO dao = new UserDAO();
			
			List<UserDTO> UserList = new ArrayList<>();
			
			UserList = dao.getUserList(dto);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("userList", UserList);
			
			response.sendRedirect("getUserList.jsp");
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
