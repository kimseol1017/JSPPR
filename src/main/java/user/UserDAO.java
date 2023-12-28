package user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import board.BoardDTO;
import common.JDBCUtil;

public class UserDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private final String USER_LOGIN = "select * from Member where m_id = ? and password = ?";
	
	private final String SIGN_IN = "insert into member (m_id, password, phone, email, addr)"
									+ "VALUES (?, ?, ?, ?, ?)";
	private final String ADMIN_SIGN_IN = "insert into member (m_id, password, phone, email, addr, role)"
			+ "VALUES (?, ?, ?, ?, ? ,?)";
	
	private final String USER_LIST = "select m_id,email,regdate from Member order by m_id ASC";
	
	
	public UserDTO login(UserDTO dto) {
		System.out.println("login 메소드 호출");
		
		UserDTO user = null;
		
		try {
			conn = JDBCUtil.getConnection();
			//USER_LOGIN = "select * from Member where m_id = ? and password = ?"
			pstmt = conn.prepareStatement(USER_LOGIN);
			
			pstmt.setString(1, dto.getM_id());
			pstmt.setString(2, dto.getPassword());
			
			System.out.println("ID : " + dto.getM_id());
			System.out.println("password : " + dto.getPassword());
			
			rs = pstmt.executeQuery();
			
			System.out.println("쿼리 실행됨 ");
			
			while (rs.next()) {
				
					user = new UserDTO();
					
					user.setM_id(rs.getString("m_id"));
					user.setPassword(rs.getString("password"));
					user.setRole(rs.getString("role"));
					user.setPhone(rs.getString("phone"));
					user.setEmail(rs.getString("email"));
					user.setRegdate(rs.getDate("regdate"));
					
					System.out.println(" - 인증 성공 :  해당 ID 가 DB에 존재합니다 - ");
					
			}
			
		}catch (Exception e) {
			System.out.println("인증시 문제가 발생 했습니다. ");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return user;
	}
	public void SignIn(UserDTO dto) {
		System.out.println("SignIn 메소드 호출");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(SIGN_IN);
			
			pstmt.setString(1,dto.getM_id());
			pstmt.setString(2,dto.getPassword());
			pstmt.setString(3,dto.getPhone());
			pstmt.setString(4,dto.getEmail());
			pstmt.setString(5,dto.getAddr());
			
			
			pstmt.executeUpdate();
			
			System.err.println("회원가입 성공");
		}catch (Exception e) {
			System.out.println("회원가입에 실패 했습니다. ");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);

		}
		
		
	}
	public void AdminSignIn(UserDTO dto) {
		System.out.println("AdminSignIn 메소드 호출");
		
		
		try {
			conn = JDBCUtil.getConnection();
			//ADMIN_SIGN_IN = "insert into member (m_id, password, phone, email, addr, role)"
			//               + "VALUES (?, ?, ?, ?, ? ,?)"
			pstmt = conn.prepareStatement(ADMIN_SIGN_IN);
			
			pstmt.setString(1,dto.getM_id());
			pstmt.setString(2,dto.getPassword());
			pstmt.setString(3,dto.getPhone());
			pstmt.setString(4,dto.getEmail());
			pstmt.setString(5,dto.getAddr());
			pstmt.setString(6,dto.getRole());
			
			System.out.println(" Role : " +dto.getRole());
			
			
			pstmt.executeUpdate();
			
			System.out.println("관리자 회원가입 성공");
		}catch (Exception e) {
			System.out.println("회원가입에 실패 했습니다. ");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);

		}
		
		
	}
	public List<UserDTO> getUserList(UserDTO dto){
		
		List<UserDTO> userList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_LIST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserDTO user = new UserDTO();
				
				user.setM_id(rs.getString("M_id"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getDate("regdate"));
			

				
				userList.add(user);
				
			}
		}catch (Exception e) {
			System.out.println("DB Select 실패");
			e.printStackTrace(); 
		}finally {
			JDBCUtil.close(rs, pstmt, conn);

		}
		
		return userList;
		
	}
	
	
}
