package myOrder.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import myOrder.dao.dishDao;
import myOrder.vo.mydishes;

/**
 * Servlet implementation class dishinfoserlvet
 */
@WebServlet("/dishinfoserlvet")
public class dishinfoserlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dishinfoserlvet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		int dishid = Integer.parseInt(request.getParameter("id"));
		System.out.print(dishid);
		dishDao my = new dishDao();
		JSONObject object=new JSONObject();
		object.put("myData", "");
		mydishes m=my.getdish(dishid);
		System.out.print(m.getDishesName());
		if(m!=null){
			object.put("myData",m);	
		}else{
			object.put("myDate","1");
		}
		PrintWriter out = response.getWriter();
		out.write(object.toString());
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
