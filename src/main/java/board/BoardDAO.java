package board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class BoardDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 게시일 과 조회수는 따로 삽입하지 않음
	
	private final String BOARD_INSERT =
			"insert into m_board (id, M_title, M_write,M_cont)"
			+ "values ((select nvl(max(id),0) + 1 from m_board), ? , ? , ?)";
	
	private final String BOARD_LIST = "select * from m_board order by id ASC";
	
	private final String BOARD_GET = "select * from m_board where id = ?";

	// 게시일 과 조회수는 수정할 수 없게함
	private final String BOARD_UPDATE = "update m_board set M_title= ?, M_write= ?, M_cont= ? where id = ?";
	
	private final String BOARD_DELETE = "delete m_board where id = ?";
	
	private final String ADD_CNT ="update m_board set cnt = cnt + 1 where id = ?";
	
	
	public void insertBoard(BoardDTO dto) {
		System.out.println("= insertBoard 기능 처리 =");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);
			
			pstmt.setString(1,dto.getM_title());
			pstmt.setString(2,dto.getM_write());
			pstmt.setString(3,dto.getM_cont());
			
			pstmt.executeUpdate();
			
			System.err.println("m_board 테이블 값 insert 성공");
		}catch (Exception e) {
			System.out.println("m_board 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
			
		}
	}
		
	public List<BoardDTO> getBoardList(BoardDTO dto){
		
		List<BoardDTO> boardList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_LIST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				
				board.setId(rs.getInt("id"));
				board.setM_title(rs.getString("m_title"));
				board.setM_write(rs.getString("m_write"));
				board.setRegdate(rs.getDate("regdate"));
				board.setM_cont(rs.getString("m_cont"));
				board.setCnt(rs.getInt("cnt"));

				
				boardList.add(board);
				
			}
		}catch (Exception e) {
			System.out.println("DB Select 실패");
			e.printStackTrace(); 
		}finally {
			JDBCUtil.close(rs, pstmt, conn);

		}
		
		return boardList;
		
	}
	
	
	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("getBoard 메소드 호출성공");
		
		//조회수 메소드 호출
		addCNT(dto);
		
		BoardDTO board = new BoardDTO();
		try {
			conn = JDBCUtil.getConnection();
			
			pstmt = conn.prepareStatement(BOARD_GET);
			pstmt.setInt(1, dto.getId());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				board.setId(rs.getInt("id"));
				board.setM_title(rs.getString("m_title"));
				board.setM_write(rs.getString("m_write"));
				board.setRegdate(rs.getDate("regdate"));
				board.setM_cont(rs.getString("m_cont"));
				board.setCnt(rs.getInt("cnt"));

				
				System.out.println("RS 레코드 저장성공");
				
			}
		}catch (Exception e) {
			System.out.println("글 상세조회 실패  ");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return board;
	}

	public void updateBoard(BoardDTO dto) {
		System.out.println("updateBoard 호출됨");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);
			
			pstmt.setString(1,dto.getM_title());
			pstmt.setString(2,dto.getM_write());
			pstmt.setString(3,dto.getM_cont());
			pstmt.setInt(4,dto.getId());
			
			pstmt.executeUpdate();
			
			System.out.println("DB 업데이트 성공");
			
		}catch (Exception e) {
			System.out.println("DB 업테이트 실패 ");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);

		}
	}
	
	public void deleteBoard(BoardDTO dto) {
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);
			
			pstmt.setInt(1, dto.getId());
			
			pstmt.executeUpdate();
			
			System.out.println("삭제완료");
			
		}catch (Exception e) {
			System.out.println("DB의 레코드 삭제 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	//조회수 
	private void addCNT(BoardDTO dto) {
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(ADD_CNT);
			 
			pstmt.setInt(1, dto.getId());
			
			pstmt.executeUpdate();
			
			System.out.println("조회수 증가");
		}catch (Exception e) {
			System.out.println("조회수 증가 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);

		}
	
	}
	
}
