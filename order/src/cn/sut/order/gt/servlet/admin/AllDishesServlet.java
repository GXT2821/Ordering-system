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
import cn.sut.order.gt.dao.DishesInfoDao;
import cn.sut.order.gt.vo.DishesInfo;

/**
 * Servlet implementation class AllDishesServlet
 */
@WebServlet("/AllDishesServlet")
public class AllDishesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllDishesServlet() {
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
		/*DishesInfoDao did = new DishesInfoDao();
		List<DishesInfo> list = did.SelectAllDishes();
		request.setAttribute("lists", list);
		request.getRequestDispatcher("pages/admin/dishesadmin.jsp").forward(request, response);*/
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject json = new JSONObject();
		json.put("myDate", "");
		
		int pageIndex=Integer.parseInt(request.getParameter("pageIndex"));
		int everyPageDataCount=Integer.parseInt(request.getParameter("everyPageDataCount"));
		DishesInfoDao did = new DishesInfoDao();
		
		int intCount=did.count();
		json.put("dataCount", intCount);
		
		int intallPage = 1;
		
		if ((intCount % everyPageDataCount) == 0) {
			intallPage = intCount / everyPageDataCount;
		} else {
			intallPage = intCount / everyPageDataCount + 1;
		}
		// 防止页码越界
		if (pageIndex < 0) {
			pageIndex = 0;
		} else if (pageIndex >= intallPage) {
			pageIndex = intallPage - 1;
		}
		json.put("pageIndex", pageIndex);
		
		List<DishesInfo> list= did.SelectDishes(Integer.valueOf(pageIndex * everyPageDataCount), Integer.valueOf(everyPageDataCount));
		DishesInfo[] usersDtoArr = null;
		if (list.size() > 0) {
			// 根据list的大小分配实体类的长度
			usersDtoArr = new DishesInfo[list.size()];
			// 给实体类数据赋值
			list.toArray(usersDtoArr);
			json.put("myData", usersDtoArr);
		}
		
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
	}

}
