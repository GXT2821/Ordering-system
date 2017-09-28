package cn.sut.order.gt.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sut.order.gt.dao.DishesInfoDao;
import cn.sut.order.gt.vo.DishesInfo;

/**
 * Servlet implementation class UpdateDishesServlet
 */
@WebServlet("/UpdateDishesServlet")
public class UpdateDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDishesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int dishesId = Integer.parseInt(request.getParameter("dishesId"));
		
		DishesInfoDao did = new DishesInfoDao();
		List<DishesInfo> list = did.SelectDishesById(dishesId);
		request.setAttribute("list", list.get(0));
		request.getRequestDispatcher("pages/admin/modifydishes.jsp").forward(request, response);
	}

}
