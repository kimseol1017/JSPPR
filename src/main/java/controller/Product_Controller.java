package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import product.ProductDAO;
import product.ProductDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import board.BoardDAO;
import board.BoardDTO;

@WebServlet("*.pr")
public class Product_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
         
    public Product_Controller() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		
		System.out.println(" pr 요청 처리 contorller ");
		
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String path = uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		System.out.println("==========================");
		
		if (path.equals("/insertProduct.pr")) {
			System.out.println("/insertProduct.pr 요청");
			
			int p_id = Integer.parseInt(request.getParameter("p_id"));
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			String content = request.getParameter("content");
			
			ProductDTO dto = new ProductDTO();
			
			dto.setP_id(p_id);
			dto.setName(name);
			dto.setPrice(price);
			dto.setContent(content);
			
			ProductDAO dao = new ProductDAO();
			dao.insertProduct(dto);
			
			response.sendRedirect("getProductList.pr");
			
			
		}else if (path.equals("/getProductList.pr")) {
			System.out.println("/getProductList.pr 요청");
			
			ProductDTO dto = new ProductDTO();
			ProductDAO dao = new ProductDAO();
			
			List<ProductDTO> productList = new ArrayList<>();
			
			productList = dao.getProductList(dto);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("productList", productList);
			
			response.sendRedirect("getProductList.jsp");
			
			
		}else if (path.equals("/getProduct.pr")) {
			System.out.println("/getProduct.pr 요청");
			
			int p_id = Integer.parseInt(request.getParameter("p_id"));
			
			ProductDTO dto = new ProductDTO();
			dto.setP_id(p_id);
			
			ProductDAO dao = new ProductDAO();
			
			ProductDTO product = new ProductDTO();
			product = dao.getProduct(dto);
			
			
			HttpSession session = request.getSession();
			
			session.setAttribute("product", product);
			
			response.sendRedirect("getProduct.jsp");
			
		}else if (path.equals("/updateProduct.pr")) {
			System.out.println("updateProduct.pr 요청");
			
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			String content = request.getParameter("content");
			int p_id = Integer.parseInt(request.getParameter("p_id"));

			
			ProductDTO dto = new ProductDTO();
			dto.setName(name);
			dto.setPrice(price);
			dto.setContent(content);
			dto.setP_id(p_id);
			
			ProductDAO dao = new ProductDAO();
			dao.updateproduct(dto);
			
			response.sendRedirect("getProductList.pr");
			
			
		}else if (path.equals("/deleteProduct.pr")) {
			System.out.println("/deleteProduct.pr 요청");
			
			String pr_id = request.getParameter("p_id");
			System.out.println(pr_id);
			
			int p_id = Integer.parseInt(pr_id);
			
			ProductDTO dto = new ProductDTO();
			dto.setP_id(p_id);
			
			ProductDAO dao = new ProductDAO();
			dao.deleteProduct(dto);
			
			response.sendRedirect("getProductList.pr");
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
