package cn.sut.order.gt.servlet.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sut.order.gt.dao.DishesInfoDao;
import cn.sut.order.gt.dao.OrderInfoDao;
import cn.sut.order.gt.vo.DishesInfo;
import cn.sut.order.gt.vo.PendingAccount;

/**
 * Servlet implementation class OrderInfo
 */
@WebServlet("/OrderInfoServlet")
public class OrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderInfoServlet() {
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
		OrderInfoDao order = new OrderInfoDao();
		List<PendingAccount> list = order.SelectAllOrder();
		DishesInfoDao did = new DishesInfoDao();
		List<DishesInfo> list1 = did.SelectAllRecommendDishes();
		request.setAttribute("lists", list);
		request.setAttribute("lists1", list1);
		request.getRequestDispatcher("pages/admin/main.jsp").forward(request, response);
	}

}
