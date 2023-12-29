package product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import board.BoardDTO;
import common.JDBCUtil;

public class ProductDAO {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	private final String PRODUCT_INSERT =
			"insert into product (p_id, name, price,content)"
			+ "values (? , ? , ? , ?)";
	
	private final String PRODUCT_LIST = "select * from product order by p_id ASC";
	
	private final String PRODUCT_GET = "select * from product where p_id = ?";

	// 게시일 과 조회수는 수정할 수 없게함
	private final String PRODUCT_UPDATE = "update product set name= ?, price= ?, content= ? where p_id = ?";
	
	private final String PRODUCT_DELETE = "delete product where p_id = ?";
	
	public void insertProduct(ProductDTO dto) {
		System.out.println("= insertProduct 기능 처리 =");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(PRODUCT_INSERT);
			
			pstmt.setInt(1,dto.getP_id());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3,dto.getPrice());
			pstmt.setString(4, dto.getContent());

			pstmt.executeUpdate();
			
			System.out.println("product 테이블 값 insert 성공");
		}catch (Exception e) {
			System.out.println("product 테이블에 값이 insert에 실패 했습니다. ");
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
			
		}
	}
		
	public List<ProductDTO> getProductList(ProductDTO dto){
		
		List<ProductDTO> productList = new ArrayList<>();
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(PRODUCT_LIST);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ProductDTO product = new ProductDTO();
				
				product.setP_id(rs.getInt("p_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setContent(rs.getString("content"));
				product.setRegdate(rs.getDate("regdate"));
				
				productList.add(product);
			}
		}catch (Exception e) {
			System.out.println("DB Select 실패");
			e.printStackTrace(); 
		}finally {
			JDBCUtil.close(rs, pstmt, conn);

		}
		
		return productList;
		
	}
	
	
	public ProductDTO getProduct(ProductDTO dto) {
		System.out.println("getBoard 메소드 호출성공");
		

		
		ProductDTO product = new ProductDTO();
		try {
			conn = JDBCUtil.getConnection();
			
			pstmt = conn.prepareStatement(PRODUCT_GET);
			pstmt.setInt(1, dto.getP_id());
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				product.setP_id(rs.getInt("p_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setContent(rs.getString("content"));
				product.setRegdate(rs.getDate("regdate"));
				System.out.println(" 레코드 저장성공");
				
			}
		}catch (Exception e) {
			System.out.println("상품 상세조회 실패  ");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		
		return product;
	}

	public void updateproduct(ProductDTO dto) {
		System.out.println("updateProduct 호출됨");
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(PRODUCT_UPDATE);
			
			pstmt.setString(1,dto.getName());
			pstmt.setInt(2,dto.getPrice());
			pstmt.setString(3,dto.getContent());
			
			pstmt.executeUpdate();
			
			System.out.println("DB 업데이트 성공");
			
		}catch (Exception e) {
			System.out.println("DB 업테이트 실패 ");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);

		}
	}
	
	public void deleteProduct(ProductDTO dto) {
		
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(PRODUCT_DELETE);
			
			pstmt.setInt(1, dto.getP_id());
			
			pstmt.executeUpdate();
			
			System.out.println("삭제완료");
			
		}catch (Exception e) {
			System.out.println("DB의 레코드 삭제 실패");
			e.printStackTrace();
		}finally {
			JDBCUtil.close(pstmt, conn);
		}
		
	}
	
	
	
}
