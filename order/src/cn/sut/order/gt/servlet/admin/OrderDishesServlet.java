package cn.sut.order.gt.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.sut.order.gt.dao.OrderInfoDao;
import cn.sut.order.gt.vo.ModifyDeshes;
import cn.sut.order.gt.vo.PendingAccount;

/**
 * Servlet implementation class OrderDishServlet
 */
@WebServlet("/OrderDishesServlet")
public class OrderDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDishesServlet() {
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
		
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		JSONObject json = new JSONObject();
		json.put("msg", "");
		
		OrderInfoDao od= new OrderInfoDao();
		List<ModifyDeshes> list = od.SelectAllDishesByOrderId(orderId);
		List<PendingAccount> list1 = od.SelectOrderById(orderId);
		
		if (list.size() > 0 && list1.size() > 0) {
			json.put("data", list1.get(0));
			ModifyDeshes[] usersDtoArr = new ModifyDeshes[list.size()];
			list.toArray(usersDtoArr);
			json.put("myData", usersDtoArr);
		}else{
			json.put("msg", "错误");
			
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
