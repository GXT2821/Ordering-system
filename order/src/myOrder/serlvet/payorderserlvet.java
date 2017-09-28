package myOrder.serlvet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myOrder.dao.myOrderDao;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class payorderserlvet
 */
@WebServlet("/payorderserlvet")
public class payorderserlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payorderserlvet() {
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
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		JSONObject object=new JSONObject();
		myOrderDao m = new myOrderDao();
		Date date=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=format.format(date);
		if(m.updateorderinfo(orderId, time, 1)){
			object.put("msg", "已经向餐厅管理员发送买单信息！");
		}else{
			object.put("msg", "此订单将不结账而直接作废？");
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
