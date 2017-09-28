package myOrder.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import myOrder.dao.shopDao;

/**
 * Servlet implementation class submitshoppserlvet
 */
@WebServlet("/submitshoppserlvet")
public class submitshoppserlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public submitshoppserlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 request.setCharacterEncoding("utf-8");
		 response.setCharacterEncoding("utf-8");
		 int dishesId = Integer.parseInt(request.getParameter("dishesId"));
		 int num = Integer.parseInt(request.getParameter("nums"));
		 shopDao s = new shopDao();
		 JSONObject object=new JSONObject();
		 object.put("myData", "");
		 if(s.addshop(dishesId, num)){
			    object.put("myData", "1");
				object.put("msg", "菜品已加入点餐车！");
			}else{
				object.put("msg", "菜品添加失败！");
			}
		 PrintWriter out = response.getWriter();
			out.write(object.toString());
			out.flush();
			out.close();
	}

}
