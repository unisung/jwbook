package jwbook3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jwbook3.model.Product;
import jwbook3.service.ProductService;
import jwbook3.service.ProductServiceImpl;


@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//controller에서 사용할 객체를 필드로 선언
	ProductService service;
	
	public ProductController() {
		this.service = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String view = "";
		
		//action파라미터가 null인 경우 list페이지로 이동 처리
		if(request.getParameter("action") == null) {
			getServletContext() 
			.getRequestDispatcher("/pcontrol?action=list")
			.forward(request, response);
		}else {
			switch(action) { //http://localhost:8081/jwbook3/pcontrol?action=list
			case "list": view = list(request, response); break; 
			case "info": view = info(request, response); break;
			} //http://localhost:8081/jwbook3/pcontrol?action=info&id=1
		}
		getServletContext()
		.getRequestDispatcher("/ch08/"+view) // "/ch08/list.jsp", "/ch08/info.jsp"
		.forward(request, response);
	}


	private String info(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		Product p = service.find(id);
		request.setAttribute("p", p);
		return "productInfo.jsp"; // "/ch08/"+view -> /ch08/productInfo.jsp
	}

	private String list(HttpServletRequest request, HttpServletResponse response) {
		List<Product> list = service.findAll();
		System.out.println(list.size());
		request.setAttribute("products", list);
		return "productList.jsp"; //"/ch08/"+view -> /ch08/productList.jsp
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
