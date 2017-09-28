package cn.sut.order.gt.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sut.order.gt.dao.DishesInfoDao;
import cn.sut.order.gt.vo.DishesInfo;
import net.sf.json.JSONObject;


/**
 * Servlet implementation class DishesIsEmptyServlet
 */
@WebServlet("/DishesIsEmptyServlet")
public class DishesIsEmptyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DishesIsEmptyServlet() {
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

		
		JSONObject json = new JSONObject();
		json.put("msg", "");
		
		DishesInfoDao did = new DishesInfoDao();
		List<DishesInfo> list = did.SelectDishesByName(dishesName);
		if(list.size() > 0){
			json.put("msg", "NO");
		}else{
			json.put("msg", "OK");
		}
		
		PrintWriter out = response.getWriter();
		out.print(json.toString());
		out.flush();
		out.close();
	}

}
