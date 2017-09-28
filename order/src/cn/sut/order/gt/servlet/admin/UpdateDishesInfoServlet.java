package cn.sut.order.gt.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.sut.order.gt.dao.DishesInfoDao;

/**
 * Servlet implementation class UpdateDishesInfoServlet
 */
@WebServlet("/UpdateDishesInfoServlet")
public class UpdateDishesInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDishesInfoServlet() {
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
		
		String dishesName = request.getParameter("dishesName");
		String dishesDiscript = request.getParameter("dishesDiscript");
		String dishesTxt = request.getParameter("dishesTxt");
		Double dishesPrice = Double.parseDouble(request.getParameter("dishesPrice"));
		int recommend =Integer.parseInt(request.getParameter("recommend"));
		int dishesId = Integer.parseInt(request.getParameter("dishesId"));
		String dishesImg = request.getParameter("dishesImg");
		
		JSONObject json = new JSONObject();
		json.put("msg", "");
		
		DishesInfoDao did= new DishesInfoDao();
		if(did.UpdateDishes(dishesId, dishesName, dishesDiscript, dishesTxt, recommend, dishesPrice, dishesImg)){
			json.put("confirm", "修改成功！");
		}else{
			json.put("msg", "修改失败！");
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
		
	}

}
