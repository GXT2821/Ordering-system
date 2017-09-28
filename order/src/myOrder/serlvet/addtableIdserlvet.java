package myOrder.serlvet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myOrder.dao.myOrderDao;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class addtableIdserlvet
 */
@WebServlet("/addtableIdserlvet")
public class addtableIdserlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addtableIdserlvet() {
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
		myOrderDao m = new myOrderDao();
		JSONObject object=new JSONObject();
		object.put("myData", "");
		if((request.getParameter("tableId"))!=""){
		int tableId=Integer.parseInt(request.getParameter("tableId"));
		if(m.selectorderEndDate(tableId)==0){
				object.put("myData", tableId);
				object.put("msg", "桌号设置成功！");
		}else{
				object.put("myData", tableId);
				object.put("msg",tableId+"号桌正在使用，可更新订单！");
		}
		}else{
			object.put("msg", "还未设置桌号！");
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
